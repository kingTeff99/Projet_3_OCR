package com.ocr.safety.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocr.safety.controller.SafetyController;
import com.ocr.safety.model.AllData;
import com.ocr.safety.model.ChildAlert;
import com.ocr.safety.model.FireStation;
import com.ocr.safety.model.MedicalRecord;
import com.ocr.safety.model.Person;
import com.ocr.safety.repository.DataTreatment;


@SpringJUnitConfig
@SpringBootTest
@WebMvcTest(SafetyController.class)
public class ControllerTest {
	
	@Autowired
	private DataTreatment dataTreatment;
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	
	public static List<Person> personList = DataForTest.PersonList();

    public static List<FireStation> firestationsList = DataForTest.FirestationList();

    public static List<MedicalRecord> medicalRecordsList = DataForTest.MedicalRecordList();

    public static AllData allData = new AllData(personList, medicalRecordsList, firestationsList);
	
	@Before
	public void setup() {
		 allData = new AllData(DataForTest.PersonList(), DataForTest.MedicalRecordList()
				, DataForTest.FirestationList());
		dataTreatment.setAlldata(allData);
	}
	
//	@Test
//  public void getFirestationByIdTest() throws Exception {
//		
//        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/firestation?stationNumber={stationNumber}", 3).accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        
//        FireStationPlus fireStationPlusResult = objectMapper.readValue(result.getResponse().getContentAsString(), FireStationPlus.class);
//        assertThat(fireStationPlusResult).isEqualTo(FireStationServiceTest.getFirestationsAreaControllerTest());
//	}
	
	@Test
    public void getFirestationByIdNoFirestationFoundTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/firestation?stationNumber={stationNumber}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(mvcResult -> Assert.assertEquals("No Firestation(s) found for number : [1] !", mvcResult.getResolvedException().getMessage()));
        
        
    }
	
	@Test
    public void getChildAlertTest() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/childAlert?address={address}", "1509 Culver St")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        
        ChildAlert childAlertResult = objectMapper.readValue(result.getResponse().getContentAsString(), ChildAlert.class);
        assertThat(childAlertResult).isEqualTo(PersonServiceTest.getChildAlertTest());
	}
	
	@Test
    public void getChildAlertNoFoundTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/childAlert?address={address}", "1 ter des gemeaux")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(mvcResult -> Assert.assertEquals("No child(ren) found for address : 1 ter des gemeaux !", mvcResult.getResolvedException().getMessage()));
    }
	
//	@Test
//    public void getPhoneAlertFromFirestationsTest() throws Exception {
//        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/phoneAlert?firestation={firestation_number}", 2)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        
//        List<String> phoneNumberListResult = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
//        assertThat(phoneNumberListResult).isEqualTo(FireStationServiceTest.getPhoneAlertListTest());
//        
//	}
	
	
	@Test
    public void getPhoneAlertFromFirestationsNoFirestationFoundTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/phoneAlert?firestation={firestation_number}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(mvcResult -> Assert.assertEquals("No Firestation(s) found for number : [1] !", mvcResult.getResolvedException().getMessage()));
    }
	
//	@Test
//    public void getCompletePersonByAddressIfFireTest() throws Exception {
//        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/fire?address={address}", "1509 Culver St")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        
//        Fire medicalRecordResult = objectMapper.readValue(result.getResponse().getContentAsString(), Fire.class);
//        assertThat(medicalRecordResult).isEqualTo(FireStationServiceTest.getCompletePersonListFireStationNumberThreeTest());
//        
//	}
	
	
	@Test
    public void getompletePersonByAddressIfFireNoPersonFoundTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/fire?address={address}", "1 ter des gemeaux")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(mvcResult -> Assert.assertEquals("No person(s) found for address : 1 ter des gemeaux !", mvcResult.getResolvedException().getMessage()));
    }
	
//	@Test
//    public void getCompletePersonByStationsListTest() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/flood/stations?stations={list of station numbers}", new int[]{3, 2})
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
	
	
	@Test
    public void getCompletePersonByItsNamesNoPersonFoundFromFirstNameAndNameTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/personInfo?firstName={firstName}&lastName={LastName}", "Jean", "Boyd")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(mvcResult -> Assert.assertEquals("No person named : Jean Boyd found!", mvcResult.getResolvedException().getMessage()));
    }
	
	@Test
    public void getCompletePersonByItsNamesNoPersonFoundFromNameTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/personInfo?firstName={firstName}&lastName={LastName}", null, "Paulo")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(mvcResult -> Assert.assertEquals("No person named : Paulo found!", mvcResult.getResolvedException().getMessage()));
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
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(mvcResult -> Assert.assertEquals("No person(s) found!", mvcResult.getResolvedException().getMessage()));
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
        
        Person personResult = objectMapper.readValue(result.getResponse().getContentAsString(), Person.class);
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
        
        Person personResult = objectMapper.readValue(result.getResponse().getContentAsString(), Person.class);
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
        
        MedicalRecord medicalRecordResult = objectMapper.readValue(result.getResponse().getContentAsString(), MedicalRecord.class);
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
	        
	        MedicalRecord medicalRecordResult = objectMapper.readValue(result.getResponse().getContentAsString(), MedicalRecord.class);
	        assertThat(medicalRecordResult).isEqualTo(DataForTest.getMedicalRecordToUpdateTest());
	        
	 }
	 
	 @Test
	 public void deleteMedicalRecordTest() throws Exception {
		 
	        String jsonMedicalRecordToDelete = objectMapper.writeValueAsString(DataForTest.getMedicalRecordToDeleteTest());

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
	        
	        FireStation firestationResult = objectMapper.readValue(result.getResponse().getContentAsString(), FireStation.class);
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
	                .andExpect(MockMvcResultMatchers.content().string("true"))
	                .andReturn();
	  }

	

}
