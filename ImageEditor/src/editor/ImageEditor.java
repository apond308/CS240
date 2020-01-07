package editor;

import java.io.*;
import java.util.Scanner;

public class ImageEditor {

    enum image_operation {
        INVERT, GRAYSCALE, EMBOSS, MOTIONBLUR;
    }

    public static void main(String args[]) throws IOException {

        System.out.println("arg 0");
        System.out.println(args[0]);
        System.out.println("arg 1");
        System.out.println(args[1]);
        System.out.println("arg 2");
        System.out.println(args[2]);
        System.out.println("arg 3");
        System.out.println(args[3]);
        System.out.println("\n");

        File input_file = new File(args[0]);
        Scanner file_reader = new Scanner(input_file);

        BufferedWriter file_writer = new BufferedWriter(new FileWriter(args[1]));
        String output_string = "P3";

        String image_type = file_reader.next();
//        if (image_type == "P3")
//            file_writer.write("P3");
//        else
//            System.out.println("Invalid image type.");

        String image_width = file_reader.next();
        output_string += image_width;
        String image_height = file_reader.next();
        output_string += image_height;
        String max_color_value = file_reader.next();
        output_string += max_color_value;


        int count = 0;

        while(file_reader.hasNextLine() && count++<10)
        {
            String value = file_reader.next();
            System.out.println(value);
            file_writer.write(value);
        }
        file_writer.close();

    }

}
