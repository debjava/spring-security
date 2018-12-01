package com.ddlab.rnd.spring.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ddlab.rnd.spring.entity.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/emc")
@RestController
public class EmcResource {
	
	private static String DIR_PATH = "E:/spring-boot-security-oxygen-2017/springbootspringrest/temp";

	@RequestMapping(value = "/address", method = RequestMethod.GET, produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> getDefaultAddress() {
		// http://localhost:8090/springbootrest/emc/address
		String emcBlrAdrs = "Dell EMC Corporation, Bengaluru, Karnataka 560048";
		return new ResponseEntity<String>(emcBlrAdrs, HttpStatus.OK);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~ @PathParam @PathVariable ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// USA,UK,INDIA
	@RequestMapping(value = "/address/{areaCode}", method = RequestMethod.GET, produces = {
			MediaType.TEXT_PLAIN_VALUE })
	public String getAddressByCode(@PathVariable String areaCode) {
		// http://localhost:8090/springbootrest/emc/address/usa
		try {
			if (areaCode.equalsIgnoreCase("USA"))
				return "Dell Emc , 176 South Street\r\n" + "Hopkinton MA 01748";
			else if (areaCode.equalsIgnoreCase("UK"))
				return "EMC Corporation\r\n" + "EMC Tower, Great West Road\r\n" + "Brentford Middlesex\r\n"
						+ "London TW89AN";
			else if (areaCode.equalsIgnoreCase("India"))
				return "Dell EMC Corporation, Bengaluru, Karnataka 560048";
			else
				return "No such area code exists for EMC";
		} catch (Exception e) {
			return "No such area code exists for ITC";
		}
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~ @QueryParam @RequestParam ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// USA,UK,INDIA
	@RequestMapping(value = "/contact", method = RequestMethod.GET, produces = { MediaType.TEXT_PLAIN_VALUE })
	public String getAddressByCountry(@RequestParam String country) {
		// http://localhost:8090/springbootrest/emc/contact?country=uk
		try {
			if (country.equalsIgnoreCase("USA"))
				return "Dell Emc , 176 South Street\r\n" + "Hopkinton MA 01748";
			else if (country.equalsIgnoreCase("UK"))
				return "EMC Corporation\r\n" + "EMC Tower, Great West Road\r\n" + "Brentford Middlesex\r\n"
						+ "London TW89AN";
			else if (country.equalsIgnoreCase("India"))
				return "Dell EMC Corporation, Bengaluru, Karnataka 560048";
			else
				return "No such area code exists for EMC";
		} catch (Exception e) {
			return "No such area code exists for ITC";
		}
	}
	
//	// ~~~~~~~~~~~~~~~~~~~~~~~ @MatrixParam ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		@RequestMapping(value = "/matrix", method = RequestMethod.GET, produces = { MediaType.TEXT_PLAIN_VALUE })
//		public ResponseEntity<?> getITCAddress(@MatrixVariable(pathVar="matrix", value="country") String country, @MatrixVariable(pathVar="matrix", value="division") String division) {
//			String details = null;
//			if( country.equalsIgnoreCase("USA") || division.equalsIgnoreCase("cloud"))
//				details = "Cloud division of Emc US";
//			else 
//				details = division+" division of Emc "+country;
//			return new ResponseEntity<String>(details, HttpStatus.OK);
//		}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~ @FormParam ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		@RequestMapping(value = "/form1", method = RequestMethod.POST, produces = { MediaType.TEXT_PLAIN_VALUE })
		public ResponseEntity<String> postNGetEmcAddress(@RequestParam("country") String country,
				@RequestParam("areaCode") String areaCode) {
			//http://localhost:8090/springbootrest/emc/form1
			//Content-Type: application/x-www-form-urlencoded
			//country: USA
			//areaCode: MA
			String details = country+"---"+areaCode;
			return new ResponseEntity<String>(details, HttpStatus.OK);
		}



	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> createEmp(@RequestBody User user) throws Exception {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<User>(user, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/create1", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> createEmp1(@RequestBody String userStr)
			throws NullPointerException, JsonParseException, JsonMappingException, IOException {
		System.out.println("Now userStr---------->"+userStr);
		ObjectMapper mapper = new ObjectMapper();
		User user = null;
		try {
			user = mapper.readValue(userStr, User.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Invalid Input Json Format");
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		// responseHeaders.setLocation( new URL("/create").toURI());
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<User>(user, responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value={"/upload"}, method={RequestMethod.POST}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
		//http://localhost:8090/springbootrest/emc/upload
		//Content-Type : multipart/form-data
		//Form data as file: <File path>
		if (file.isEmpty()) {
			throw new IllegalArgumentException("Invalid file, upload a correct file.");
		}
		try {
			byte[] bytes = file.getBytes();
	        Path path = Paths.get( DIR_PATH+File.separator+file.getOriginalFilename());
	        Files.write(path, bytes);
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("File uploaded successfully",HttpStatus.OK);
	}
	
	//http://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html
	
	@RequestMapping(path = "/download1", method = RequestMethod.GET)
	public ResponseEntity<Resource> download(String param) throws IOException {
		//http://localhost:8090/springbootrest/emc/download1
		File file = new File(DIR_PATH+File.separator+"rtiodisha.pdf");
	    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
	    String attachment = "attachment; filename="+file.getName();
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Disposition", attachment);
	    
	    return ResponseEntity.ok()
	            .headers(responseHeaders)
	            .contentLength(file.length())
	            .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
//	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .body(resource);
	}

}
