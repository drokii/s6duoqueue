package auth.dtos;

public class UserDTO {
    String bio;
    String website;
    String location;
    String username;

    public UserDTO(String bio, String website, String location, String username) {
        this.bio = bio;
        this.website = website;
        this.location = location;
        this.username = username;
    }
}
