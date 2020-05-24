package agh.aspm.aspm.resource;

import agh.aspm.aspm.model.MoldingDTO;
import agh.aspm.aspm.service.CSVDataLoader;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ASPMResource {

    @Autowired
    private HttpServletRequest request;

    private CSVDataLoader csvDataLoader;

    @PostMapping("/csv")
    public List<MoldingDTO> getDataFromCSV(@RequestBody String url) {
        return csvDataLoader.getCSVFile(url);
    }

    @PostMapping("csv/{speed}")
    public List<MoldingDTO> getDataBySpeed(@PathVariable("speed") String speed, @RequestBody String url) {
        return csvDataLoader.getBySpeed(speed, url);
    }

    @PostMapping(value = "/csv/save")
    public List<String> saveFile(@RequestParam("file") MultipartFile file) {
        return csvDataLoader.saveFile(file, request);
    }

}
