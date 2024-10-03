package com.example.auth.domain.questions;

import lombok.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {

    private String imageBase64;
    private String message;
    private String neurodiversityOption;
    private String importantWords;
















    public static String convertBase64ToJpeg(String base64Image, String outputFilePath) {
        try {
            // Decodifica a string base64 para obter os bytes da imagem
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);

            // Converte os bytes em uma BufferedImage
            ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
            BufferedImage image = ImageIO.read(bais);

            // Verifica se a imagem foi lida corretamente
            if (image == null) {
                System.out.println("A imagem não pôde ser lida a partir dos bytes fornecidos.");
                return null;
            }

            // Salva a BufferedImage como um arquivo .jpg
            File outputFile = new File(outputFilePath);
            ImageIO.write(image, "jpg", outputFile);

            return outputFilePath;
        } catch (IOException e) {
           return e.getMessage();
        }
    }
}

