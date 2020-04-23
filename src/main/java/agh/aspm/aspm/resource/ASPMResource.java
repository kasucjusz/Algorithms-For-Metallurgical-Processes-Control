package agh.aspm.aspm.resource;

import agh.aspm.aspm.model.MoldingDTO;
import agh.aspm.aspm.service.CSVDataLoader;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ASPMResource {

    private CSVDataLoader csvDataLoader;

    @GetMapping("/csv")
    public List<MoldingDTO> getDataFromCSV() {
        return csvDataLoader.getCSVFile();
    }

    @GetMapping("csv/{speed}")
    public List<MoldingDTO> getDataBySpeed(@PathVariable("speed") String speed) {
        return csvDataLoader.getBySpeed(speed);
    }

}
