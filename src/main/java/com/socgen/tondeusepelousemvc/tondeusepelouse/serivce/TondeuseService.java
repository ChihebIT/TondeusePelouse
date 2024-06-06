package com.socgen.tondeusepelousemvc.tondeusepelouse.serivce;
import com.socgen.tondeusepelousemvc.tondeusepelouse.model.MaxDimensionsPelouse;
import com.socgen.tondeusepelousemvc.tondeusepelouse.model.OrientationEnum;
import com.socgen.tondeusepelousemvc.tondeusepelouse.model.Position;
import com.socgen.tondeusepelousemvc.tondeusepelouse.model.Tondeuse;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TondeuseService {

    public String execution(BufferedReader reader) throws IOException {
        StringBuilder result = new StringBuilder();
        MaxDimensionsPelouse maxDimensionsPelouse = getMaxDimensionsPelouse(reader);
        List<Tondeuse> tondeuses = new ArrayList<>();

        extractedExecuterDeplacement(reader, maxDimensionsPelouse, tondeuses);

        tondeuses.forEach(tondeuse -> result.append(tondeuse.getPosition().getX()).append(" ").append(tondeuse.getPosition().getY()).append(" ").append(tondeuse.getPosition().getOrientation()).append("\n"));
        return result.toString();
    }

    public void extractedExecuterDeplacement(BufferedReader reader, MaxDimensionsPelouse maxDimensionsPelouse, List<Tondeuse> tondeuses) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] positionInitiale = line.split(" ");
            Tondeuse tondeuse = Tondeuse.builder()
                    .position(Position.builder()
                            .x(Integer.parseInt(positionInitiale[0]))
                            .y(Integer.parseInt(positionInitiale[1]))
                            .orientation(OrientationEnum.valueOf(positionInitiale[2]))
                            .build())
                    .build();
            tondeuses.add(tondeuse);
            String instructions = reader.readLine();
            tondeuse.executerDeplacement(instructions, maxDimensionsPelouse.maxX(), maxDimensionsPelouse.maxY());
        }
    }

    public MaxDimensionsPelouse getMaxDimensionsPelouse(BufferedReader reader) throws IOException {
        String[] dimensionsPelouse = reader.readLine().split(" ");
        int maxX = Integer.parseInt(dimensionsPelouse[0]);
        int maxY = Integer.parseInt(dimensionsPelouse[1]);
        return new MaxDimensionsPelouse(maxX, maxY);
    }

}
