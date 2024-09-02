package com.project.quizletclone.service;

import com.project.quizletclone.model.Bookmark;
import com.project.quizletclone.model.User;
import com.project.quizletclone.model.Quiz;
import com.project.quizletclone.model.Performance;
import com.project.quizletclone.model.Achievement;
import com.project.quizletclone.repository.BookmarkRepository;
import com.project.quizletclone.repository.UserRepository;
import com.project.quizletclone.repository.QuizRepository;
import com.project.quizletclone.repository.PerformanceRepository;
import com.project.quizletclone.repository.AchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Directory to save uploaded files
    private final Path uploadPath = Paths.get("src/main/resources/static/uploads").toAbsolutePath().normalize();

    public UserService() {
        // Create the directory if it doesn't exist
        try {
            Files.createDirectories(uploadPath);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public User registerUser(String username, String password, String email) {
        if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);

        return userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    private String saveAvatarFile(MultipartFile avatar) throws IOException {
        // Ensure the file is not empty
        if (avatar.isEmpty()) {
            throw new RuntimeException("Failed to store empty file.");
        }

//        // Normalize the file name and handle any potential path traversal issues
//        String fileName = UUID.randomUUID().toString() + "-" + avatar.getOriginalFilename();
//        Path targetLocation = fileStorageLocation.resolve(fileName);
//
//        try {
//            // Copy the file to the target location (replacing any existing file)
//            Files.copy(avatar.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException ex) {
//            throw new RuntimeException("Could not store file. Please try again!", ex);
//        }
//
//        // Return the relative path or URL of the file
//        return targetLocation.toString(); // can also return a relative path if needed

        // Get the original file name including its extension
        String fileName = avatar.getOriginalFilename();
        if (fileName == null || fileName.isEmpty()) {
            throw new RuntimeException("Failed to get the original file name.");
        }
        // Define the target file path
        Path targetPath = uploadPath.resolve(fileName);
        // Save the file to the target path
        try (var inputStream = avatar.getInputStream()) {
            Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
        }
        // Return the file name

        System.out.println("\n UPLOAD AVATAR" + fileName + "\n");
        return fileName;
    }

    private String copySelectedAvatar(String selectedAvatar) throws IOException {

        // Get the file name from the selected avatar path
        String fileName = selectedAvatar.substring(selectedAvatar.lastIndexOf('/') + 1);

        System.out.println("\n" + selectedAvatar + "\n");
        System.out.println("\n" + fileName + "\n");

        // Define the source and target paths
        Path sourcePath = Paths.get("src/main/resources/static" + selectedAvatar).toAbsolutePath().normalize();
        Path targetPath = uploadPath.resolve(fileName);

        // Ensure the source file exists before copying
        if (!Files.exists(sourcePath)) {
            throw new IOException("Source file does not exist: " + sourcePath);
        }

        // Copy the file from the source to the target location
        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

        // Return the file name to be stored in the user's avatar path

        System.out.println("\n DEFAULT AVATAR" + fileName + "\n");
        return fileName;
    }

    public User updateUser(Long id, String username, String email, MultipartFile avatar, String selectedAvatar) throws IOException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (username != null && !username.isEmpty()) {
            user.setUsername(username);
        }
        if (email != null && !email.isEmpty()) {
            user.setEmail(email);
        }
        if (avatar != null && !avatar.isEmpty()) {
            // Handle avatar file saving (this would involve saving the file somewhere and setting the path on the user)
            String avatarPath = saveAvatarFile(avatar); // This method should save the file and return the path
            System.out.println("\nTEST HERE 1" + avatarPath + "\n");
            user.setAvatarPath(avatarPath);
        } else if (selectedAvatar != null && !selectedAvatar.isEmpty()) {
            // Copy the selected default avatar to the uploads directory
            String avatarPath = copySelectedAvatar(selectedAvatar);
            System.out.println("\n TEST HERE 2" + avatarPath + "\n");
            user.setAvatarPath(avatarPath);
        }

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        userRepository.delete(user);
    }

    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Additional methods for user management can be added here
}
