package nl.ekholabs.escode.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.ws.RequestWrapper;

import nl.ekholabs.escode.action.GenerateEsCodeAction;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GenerateEsCodeController {

  @RequestMapping("/")
  @ResponseBody
  public String Home()
  {
    return "Hello world!";
  }
  
	@RequestMapping(method = RequestMethod.GET, value = "/generate")
	public String provideInfo(Model model) {
	  // sorting file
		File rootFolder = new File(EsCode.ROOT_GENERATE);
		List<String> fileNames = Arrays.stream(rootFolder.listFiles())
        .sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
        .map(f -> f.getName())
        .collect(Collectors.toList());

		model.addAttribute("files",fileNames);

		return "generateForm";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/generate")
	public String handleFileUpload(
	                 @RequestParam("file") MultipartFile file,
								   RedirectAttributes redirectAttributes) {
		if (!file.isEmpty()) {
			try {
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(EsCode.ROOT_GENERATE + "/" + file.getOriginalFilename())));
        FileCopyUtils.copy(file.getInputStream(), stream);   
        stream.close();
        redirectAttributes.addFlashAttribute("message",
            "You successfully generated the ES-Code image for " + file.getOriginalFilename() + "!");
				//generating
				File uploadedFile=new File(EsCode.ROOT_GENERATE+"/",file.getOriginalFilename());
				new GenerateEsCodeAction(uploadedFile).generateFile();
			}
			catch (Exception e) {
				redirectAttributes.addFlashAttribute("message",
						"You failed to generated " + file.getOriginalFilename() + " => " + e.getMessage());
			}
		}
		else {
			redirectAttributes.addFlashAttribute("message",
					"You failed to generated " + file.getOriginalFilename() + " because the file uploaded was empty");
		}

		return "redirect:generate";
	}
}
