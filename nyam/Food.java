package nyam;

public class Food {
    public enum Variant {
        BREAKFAST,
        LUNCH,
        DINNER
    }

    private String name;
    private Variant variant;
    private String image;

    public Food() {
    }

    public Food(Variant variant, String name, String image) {
        this.variant = variant;
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
