package service.hobbyservice.dto.request;

public class ImageUploadResponseDto {
    private String imageUrl;

    public ImageUploadResponseDto(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Getters and setters
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}