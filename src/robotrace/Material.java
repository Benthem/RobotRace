package robotrace;

/**
* Materials that can be used for the robots.
*/
public enum Material {

    /** 
     * Gold material properties.
     * Modify the default values to make it look like gold.
     */
    GOLD (
        new float[] {0.75f, 0.60f, 0.22f, 1.0f},
        new float[] {0.62f, 0.55f, 0.36f, 1.0f},
            5.12f),
 
    /**
     * Silver material properties.
     * Modify the default values to make it look like silver.
     */
    SILVER (
        new float[] {0.51f, 0.51f, 0.51f, 1.0f},
        new float[] {0.80f, 0.80f, 0.80f, 1.0f},
        5.12f),

    /** 
     * Wood material properties.
     * Modify the default values to make it look like wood.
     */
    WOOD (
            new float[] {0.29f, 0.17f, 0.05f, 1.0f},
            new float[] {0.0f, 0.0f, 0.0f, 1.0f},
        0f),

    /**
     * Orange material properties.
     * Modify the default values to make it look like orange.
     */
    ORANGE (
        new float[] {0.9f, 0.4f, 0.0f, 1.0f},
        new float[] {0.0f, 0.0f, 0.0f, 1.0f},
        0f);

    /** The diffuse RGBA reflectance of the material. */
    float[] diffuse;

    /** The specular RGBA reflectance of the material. */
    float[] specular;
    
    /** The specular exponent of the material. */
    float shininess;

    /**
     * Constructs a new material with diffuse and specular properties.
     */
    private Material(float[] diffuse, float[] specular, float shininess) {
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;
    }
}
