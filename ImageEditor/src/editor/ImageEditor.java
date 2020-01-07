package editor;

import passoff.Pixel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageEditor {

    enum image_operation {
        INVERT, GRAYSCALE, EMBOSS, MOTIONBLUR;
    }

    public static void main(String args[]) throws IOException {

        BufferedWriter file_writer = new BufferedWriter(new FileWriter(args[1]));
        StringBuilder output_string = new StringBuilder("P3");
        output_string.append(" ");

        String[] input_file = Files.readString(Paths.get(args[0])).split("\\s+");
        int index = 1;

        int image_width = Integer.parseInt(input_file[index++]);
        output_string.append(image_width);
        output_string.append(" ");
        int image_height = Integer.parseInt(input_file[index++]);
        output_string.append(image_height);
        output_string.append(" ");
        String max_color_value = input_file[index++];
        output_string.append(max_color_value);
        output_string.append(" ");

        Pixel[][] image_array = new Pixel[image_height][image_width];

        int x_pos = 0;
        int y_pos = 0;
        while(index < input_file.length)
        {
            int red = Integer.parseInt(input_file[index++]);
            int green = Integer.parseInt(input_file[index++]);
            int blue = Integer.parseInt(input_file[index++]);

            image_array[y_pos][x_pos++] = new Pixel(red, green, blue);
            if (x_pos >= image_width) {
                x_pos = 0;
                y_pos++;
            }
        }

        for (int y=0;y<image_height;y++)
        {
            for (int x=0;x<image_width;x++)
            {
                int red = image_array[y][x].red;
                int green = image_array[y][x].green;
                int blue = image_array[y][x].blue;

                if (args[2].equals("invert"))
                {
                    red = 255-red;
                    green = 255-green;
                    blue = 255-blue;
                }
                else if (args[2].equals("grayscale"))
                {
                    int average = (red + green + blue)/3;
                    red = average;
                    green = average;
                    blue = average;
                }
                else if (args[2].equals("emboss"))
                {
                    int v = 128;
                    if (x==1 && y==1)
                        System.out.println();
                    if (x != 0 && y != 0) {
                        int red_diff = red - image_array[y-1][x-1].red;
                        int green_diff = green - image_array[y-1][x-1].green;
                        int blue_diff = blue - image_array[y-1][x-1].blue;
                        int diff = red_diff;
                        if (Math.abs(green_diff) > Math.abs(diff))
                            diff = green_diff;
                        if (Math.abs(blue_diff) > Math.abs(diff))
                            diff = blue_diff;
                        v += diff;
                        v = Math.min(255, v);
                        v = Math.max(0, v);
                    }
                    red = v;
                    green = v;
                    blue = v;
                }
                else if (args[2].equals("motionblur"))
                {
                    int magnitude = Integer.parseInt(args[3]);
                    Pixel pixel_average = new Pixel(image_array[y][x].red, image_array[y][x].green, image_array[y][x].blue);
                    int count = 1;
                    for (int i=x+1;i<x+magnitude && i<image_width;i++)
                    {
                        pixel_average.red += image_array[y][i].red;
                        pixel_average.green += image_array[y][i].green;
                        pixel_average.blue += image_array[y][i].blue;
                        count++;
                    }
                    red = pixel_average.red/count;
                    green = pixel_average.green/count;
                    blue = pixel_average.blue/count;
                }

                output_string.append(String.valueOf(red) + " ");
                output_string.append(String.valueOf(green) + " ");
                output_string.append(String.valueOf(blue) + " ");
            }
        }

        file_writer.write(String.valueOf(output_string));
        file_writer.close();

    }

}
