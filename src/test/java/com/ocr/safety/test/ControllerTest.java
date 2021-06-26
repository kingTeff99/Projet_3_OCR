package com.ocr.safety.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocr.safety.model.AllData;
import com.ocr.safety.model.ChildAlert;
import com.ocr.safety.model.Fire;
import com.ocr.safety.model.FireStation;
import com.ocr.safety.model.FireStationPlus;
import com.ocr.safety.model.MedicalRecord;
import com.ocr.safety.model.Person;
import com.ocr.safety.repository.DataTreatment;
import com.ocr.safety.service.FirestationService;
import com.ocr.safety.service.MedicalrecordService;
import com.ocr.safety.service.PersonService;


//@SpringJUnitConfig
//@WebMvcTest(SafetyController.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ControllerTest {
	
	@MockBean
	private DataTreatment dataTreatment;
	
	@MockBean
	private FirestationService firestationService;
	
	@MockBean
	private MedicalrecordService medicalrecordService;
	
	@MockBean
	private PersonService personService;
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
    private ObjectMapper objectMapper;
	
	public static List<Person> personList = DataForTest.PersonList();

    public static List<FireStation> firestationsList = DataForTest.FirestationList();

    public static List<MedicalRecord> medicalRecordsList = DataForTest.MedicalRecordList();

    public AllData allDataTest;
	
	@BeforeEach
	public void setup() {
		
		allDataTest = new AllData(DataForTest.PersonList(), DataForTest.MedicalRecordList()
				, DataForTest.FirestationList());
		 
		dataTreatment.setAlldata(allDataTest);
		
	}
	
	@AfterEach
	public void endUp() {
		
	}
	
	@Test
	public void getFirestationByStationIdTest() throws Exception {
		
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/firestation?stationNumber={stationNumber}", 3)
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        
        FireStationPlus fireStationPlusResult = objectMapper.readValue(result.getResponse()
        		.getContentAsString(), FireStationPlus.class);
        
        assertThat(fireStationPlusResult).isEqualTo(FireStationServiceTest.getFirestationsAreaControllerTest());
        
	}
	
	@Test
    public void getFirestationByIdNoFirestationFoundTest() throws Exception {
		
        mvc.perform(MockMvcRequestBuilders.get("/firestation?stationNumber={stationNumber}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(""));
        
        
    }
	
	@Test
    public void getChildAlertTest() throws Exception {
		
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/childAlert?address={address}", "1509 Culver St")
        		.contentType(MediaType.APPLICATION_JSON)
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        
        
        assertEquals(PersonServiceTest.getChildAlertTest(), objectMapper.readValue(result.getResponse().
        		getContentAsString(StandardCharsets.UTF_8), ChildAlert.class));
        
	}
	
	@Test
    public void getChildAlertNoFoundTest() throws Exception {
		
        mvc.perform(MockMvcRequestBuilders.get("/childAlert?address={address}", "1 avenue papin")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
                	
    }
	
	@Test
	public void getPhoneAlertFromFirestationsTest() throws Exception {
	
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/phoneAlert?firestation={firestation_number}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        
		List<String> phoneNumberListResult = objectMapper.readValue(result.getResponse()
				.getContentAsString(), List.class);
   
		assertThat(phoneNumberListResult).isEqualTo(FireStationServiceTest.getPhoneAlertListTest());
        
	}
	
	
	@Test
    public void getPhoneAlertFromFirestationsNoFirestationFoundTest() throws Exception {
		
        mvc.perform(MockMvcRequestBuilders.get("/phoneAlert?firestation={firestation_number}", 5)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("[]"));
    }
	
	@Test
	public void getCompletePersonByAddressIfFireTest() throws Exception {
		
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/fire?address={address}", "1509 Culver St")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        
        Fire medicalRecordResult = objectMapper.readValue(result.getResponse().getContentAsString(), Fire.class);
        
        assertThat(medicalRecordResult).isEqualTo(FireStationServiceTest
        		.getCompletePersonListFireStationNumberThreeTest());
        
	}
	
	
	@Test
    public void getCompletePersonByAddressIfFireNoPersonFoundTest() throws Exception {
		
        mvc.perform(MockMvcRequestBuilders.get("/fire?address={address}", "1 ter des gemeaux")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(""));
        
    }
	
	@Test
    public void getCompletePersonByStationsListTest() throws Exception {
		
        mvc.perform(MockMvcRequestBuilders.get("/flood/stations?stations={list of station numbers}", new int[]{3, 2})
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
	
	
	@Test
    public void getCompletePersonByItsNamesNoPersonFoundFromFirstNameAndNameTest() throws Exception {
		
        mvc.perform(MockMvcRequestBuilders.get("/personInfo?firstName={firstName}&lastName={LastName}", "Jean", "Boyd")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("[]"));
        
    }
	
	@Test
    public void getCompletePersonByItsNamesNoPersonFoundFromNameTest() throws Exception {
		
        mvc.perform(MockMvcRequestBuilders.get("/personInfo?firstName={firstName}&lastName={LastName}", null, "Theo")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("[]"));
        
    }
	
	@Test
    public void getEmailsFromCityTest() throws Exception {
		
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/communityEmail?city={city}", "Culver")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        
        List<String> emailsResult = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
        
        assertThat(emailsResult).isEqualTo(PersonServiceTest.getEmailsFromCityList());
        
	}
	
	
	@Test
	public void getEmailsFromCityNoPersonFoundTest() throws Exception {
		
        mvc.perform(MockMvcRequestBuilders.get("/communityEmail?city={city}", "London")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(""));
    }
	
	@Test
	public void savePersonTest() throws Exception {
		
        String jsonPersonToAdd = objectMapper.writeValueAsString(DataForTest.getPersonToAddTest());

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/person")
                .content(jsonPersonToAdd)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        
        Person personResult = objectMapper.readValue(result.getResponse()
        		.getContentAsString(), Person.class);
        
        assertThat(personResult).isEqualTo(DataForTest.getPersonToAddTest());
        
	}
	
	@Test
	public void updatePersonTest() throws Exception {
		
        String jsonPersonToUpdate = objectMapper.writeValueAsString(DataForTest.getPersonToUpdateTest());

        MvcResult result = mvc.perform(MockMvcRequestBuilders.put("/person")
        		.content(jsonPersonToUpdate)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        
        Person personResult = objectMapper.readValue(result.getResponse()
        		.getContentAsString(), Person.class);
        
        assertThat(personResult).isEqualTo(DataForTest.getPersonToUpdateTest());
        
	}
	
	@Test
	public void deletePersonTest() throws Exception {
		
        String jsonPersonToDelete = objectMapper.writeValueAsString(DataForTest.getPersonToDeleteTest());

        mvc.perform(MockMvcRequestBuilders.delete("/person")
                .content(jsonPersonToDelete)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("true"))
                .andReturn();
    }
	
	@Test
	public void saveMedicalRecordTest() throws Exception {
		
		String jsonMedicalRecordToAdd = objectMapper.writeValueAsString(DataForTest.getMedicalRecordToAddTest());

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/medicalRecord")
                .content(jsonMedicalRecordToAdd)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        
        MedicalRecord medicalRecordResult = objectMapper.readValue(result.getResponse()
        		.getContentAsString(), MedicalRecord.class);
        
        assertThat(medicalRecordResult).isEqualTo(DataForTest.getMedicalRecordToAddTest());
        
	}
	
	 @Test
	 public void updateMedicalRecordTest() throws Exception {
		 
		 String jsonMedicalRecordToUpdate = objectMapper.writeValueAsString(DataForTest.getMedicalRecordToUpdateTest());

	        MvcResult result = mvc.perform(MockMvcRequestBuilders.put("/medicalRecord")
	                .content(jsonMedicalRecordToUpdate)
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andReturn();
	        
	        MedicalRecord medicalRecordResult = objectMapper.readValue(result.getResponse()
	        		.getContentAsString(), MedicalRecord.class);
	        
	        assertThat(medicalRecordResult).isEqualTo(DataForTest.getMedicalRecordToUpdateTest());
	        
	 }
	 
	 @Test
	 public void deleteMedicalRecordTest() throws Exception {
		 
	        String jsonMedicalRecordToDelete = objectMapper.writeValueAsString(DataForTest
	        		.getMedicalRecordToDeleteTest());

	        mvc.perform(MockMvcRequestBuilders.delete("/medicalRecord")
	        		.content(jsonMedicalRecordToDelete)
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.content().string("true"))
	                .andReturn();
	 }
	 
	 @Test
	 public void saveFirestationTest() throws Exception {
		 
	        String jsonFirestationToAdd = objectMapper.writeValueAsString(DataForTest.getFireStationToAdd());

	        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/firestation")
	        		.content(jsonFirestationToAdd)
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andReturn();
	        
	        FireStation firestationResult = objectMapper.readValue(result.getResponse()
	        		.getContentAsString(), FireStation.class);
	        
	        assertThat(firestationResult).isEqualTo(DataForTest.getFireStationToAdd());
	        
	 }
	 
	 @Test
	 public void deleteFirestationTest() throws Exception {
		 
	        String jsonFirestationToDelete = objectMapper.writeValueAsString(DataForTest.getFireStationToDelete());

	        mvc.perform(MockMvcRequestBuilders.delete("/firestation")
	        		.content(jsonFirestationToDelete)
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.content().string("false"))
	                .andReturn();
	 }

	

}
