package cmsc434.refridgerator.model;

public class RecipeEntry {

    private String description;
    private int image;

    public RecipeEntry(int image, String description){
        this.image = image;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
