package agh.aspm.aspm.resource;

import agh.aspm.aspm.model.MoldingDTO;
import agh.aspm.aspm.service.CSVDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ASPMResource {

    @Autowired
    private CSVDataLoader csvDataLoader;

    @GetMapping(value = "/csv")
    public List<MoldingDTO> getDataFromCSV() {
        return csvDataLoader.getCSVFile();
    }

}
