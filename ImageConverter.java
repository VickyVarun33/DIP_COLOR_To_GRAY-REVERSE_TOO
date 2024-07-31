import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageConverter {

    // Convert color image to grayscale
    public static BufferedImage colorToGray(BufferedImage colorImage) {
        BufferedImage grayImage = new BufferedImage(colorImage.getWidth(), colorImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = grayImage.getGraphics();
        g.drawImage(colorImage, 0, 0, null);
        g.dispose();
        return grayImage;
    }

    // Convert grayscale image to color
    public static BufferedImage grayToColor(BufferedImage grayImage) {
        BufferedImage colorImage = new BufferedImage(grayImage.getWidth(), grayImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < grayImage.getHeight(); y++) {
            for (int x = 0; x < grayImage.getWidth(); x++) {
                int gray = grayImage.getRGB(x, y) & 0xFF;
                int rgb = (gray << 16) + (gray << 8) + gray;
                colorImage.setRGB(x, y, rgb);
            }
        }
        return colorImage;
    }

    public static void main(String[] args) {
        try {
            // Load input images
            BufferedImage colorImage = ImageIO.read(new File("D:\\DIP_Subject\\DSC_7094.JPG"));
            BufferedImage grayImage = ImageIO.read(new File("D:\\DIP_Subject\\DSC_7109.JPG"));

            // Convert color to grayscale
            BufferedImage convertedGrayImage = colorToGray(colorImage);
            ImageIO.write(convertedGrayImage, "jpg", new File("converted_to_gray.jpg"));

            // Convert grayscale to color
            BufferedImage convertedColorImage = grayToColor(grayImage);
            ImageIO.write(convertedColorImage, "jpg", new File("converted_to_color.jpg"));

            System.out.println("Conversion completed successfully.");

        } catch (IOException e) {
            System.err.println("Error reading or writing image file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid image argument: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
