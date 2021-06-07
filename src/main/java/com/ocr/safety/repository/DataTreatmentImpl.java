package com.ocr.safety.repository;

import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.ocr.safety.model.AllData;
import com.ocr.safety.model.FireStation;
import com.ocr.safety.model.MedicalRecord;
import com.ocr.safety.model.Person;

@Repository
public class DataTreatmentImpl implements DataTreatment {
	
	private AllData alldata;
	
	public void setAlldata(AllData alldata) {
		
		this.alldata = alldata;
	}

	public AllData loadFile() {
		
		try( JsonReader reader = new JsonReader
					(new InputStreamReader(
					new FileInputStream("/Users/kingteff/Documents/workspace-sts/git/safetynet/src/main/resources/data.json"),"UTF-8"))) {
			
    	    Gson gson = new Gson();

    	    alldata = gson.fromJson(reader, AllData.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alldata; 
		
	}
	
	@Override
	public List<Person> getPersons() {
		
		return new ArrayList<>(loadFile().getPersons());
	}

	@Override
	public List<MedicalRecord> getMedicalrecords() {
		
		return new ArrayList<>(loadFile().getMedicalrecords());
	}

	@Override
	public List<FireStation> getFirestations() {
		
		return new ArrayList<>(loadFile().getFirestations());
	}

	@Override
	public Person savePerson(Person prototype) {
		
		if(prototype != null) {
			
			if(loadFile().getPersons() != null) {
				
				boolean x = loadFile().getPersons()
						.stream().noneMatch(p -> p.equals(prototype));
				
				if(x) {
					
					loadFile().getPersons().add(prototype);
					
					return prototype;
				}
				
			} else {
			
			List<Person> personsList = new ArrayList<Person>();
			
			personsList.add(prototype);
			
			loadFile().setPersons(personsList);
			
			return prototype;
			
			}
		}
	
		return null;
	}

	@Override
	public Person updatePerson(Person prototype) {
		
		if(prototype != null) {
			
			if(loadFile().getPersons() != null) {
				
				Optional<Person> personUpdate = loadFile().getPersons().stream()
						.filter(p -> prototype.getFirstName().equals(p.getFirstName()) 
								&& prototype.getLastName().equals(p.getLastName())).findFirst();
				
				if(personUpdate.isPresent()) {
					
					loadFile().getPersons().set(loadFile().getPersons().indexOf(personUpdate.get()), prototype);
					
					return prototype;
					
				}
			}
			
		} else {
			
			loadFile().setPersons(new ArrayList<>());
		}
		
		return null;
	}

	@Override
	public boolean deletePerson(Person prototype) {

		if(prototype != null) {
			
			if(loadFile().getPersons() != null) {
				
				Optional<Person> personDelete = loadFile().getPersons().stream()
						.filter(p -> prototype.getFirstName().equals(p.getFirstName()) 
								&& prototype.getLastName().equals(p.getLastName())).findFirst();
				
				if(personDelete.isPresent()) {
					
					loadFile().getPersons().remove(personDelete.get());
					
					return true;
					
				}
			}
			
		} else {
			
			loadFile().setPersons(new ArrayList<>());
		}
		return false;
	}

	@Override
	public MedicalRecord saveMedicalRecords(MedicalRecord prototype) {
		
		if(prototype != null) {
			
			if(loadFile().getMedicalrecords() != null) {
				
				boolean x = loadFile().getMedicalrecords()
						.stream().noneMatch(p -> p.equals(prototype));
				
				if(x) {
					
					loadFile().getMedicalrecords().add(prototype);
					
					return prototype;
				}
				
			} else {
			
			List<MedicalRecord> medicalRecordList = new ArrayList<MedicalRecord>();
			
			medicalRecordList.add(prototype);
			
			loadFile().setMedicalrecords(medicalRecordList);
			
			return prototype;
			
			}
		}
		return null;
	}

	@Override
	public MedicalRecord updateMedicalRecords(MedicalRecord prototype) {
		
		if(prototype != null) {
			
			if(loadFile().getMedicalrecords() != null) {
				
				Optional<MedicalRecord> medicalRecordUpdate = loadFile().getMedicalrecords().stream()
						.filter(p -> prototype.getFirstName().equals(p.getFirstName()) 
								&& prototype.getLastName().equals(p.getLastName())).findFirst();
				
				if(medicalRecordUpdate.isPresent()) {
					
					loadFile().getMedicalrecords().set(loadFile().getMedicalrecords().indexOf(medicalRecordUpdate.get()), prototype);
					
					return prototype;
					
				}
			}
			
		} else {
			
			loadFile().setMedicalrecords(new ArrayList<>());
		}
		return null;
	}

	@Override
	public boolean deleteMedicalRecords(MedicalRecord prototype) {
		
		if(prototype != null) {
			
			if(loadFile().getPersons() != null) {
				
				Optional<MedicalRecord> medicalRecordDelete = loadFile().getMedicalrecords().stream()
						.filter(p -> prototype.getFirstName().equals(p.getFirstName()) 
								&& prototype.getLastName().equals(p.getLastName())).findFirst();
				
				if(medicalRecordDelete.isPresent()) {
					
					loadFile().getMedicalrecords().remove(medicalRecordDelete.get());
					
					return true;
					
				}
			}
			
		} else {
			
			loadFile().setPersons(new ArrayList<>());
		}
		
		return false;
	}

	@Override
	public FireStation saveFireStation(FireStation prototype) {
		
		if(prototype != null) {
			
			if(loadFile().getFirestations() != null) {
				
				boolean x = loadFile().getFirestations()
						.stream().noneMatch(p -> p.equals(prototype));
				
				if(x) {
					
					loadFile().getFirestations().add(prototype);
					
					return prototype;
				}
				
			} else {
			
			List<FireStation> firestationsList = new ArrayList<FireStation>();
			
			firestationsList.add(prototype);
			
			loadFile().setFirestations(firestationsList);
			
			return prototype;
			
			}
		}
		return null;
	}


	@Override
	public boolean deleteFireStation(FireStation prototype) {
		
		if(prototype != null) {
			
			if(loadFile().getFirestations() != null) {
				
				return loadFile().getFirestations().remove(prototype);
				
			} else {
			
				loadFile().setFirestations(new ArrayList<>());
			
			}
		}
		return false;
	}

	@Override
	public Integer getAgeWithBirthDate(String birthdate) {
		
		LocalDate today = LocalDate.now();
		
	    DateTimeFormatter formatter = DateTimeFormatter
	      .ofPattern("MM/dd/yyyy");
	    
	    LocalDate parsedBirthDate = LocalDate.parse(birthdate, formatter);
	    
	    return Period.between(parsedBirthDate, today).getYears();
	    
	}

	@Override
	public Integer getAgeForPerson(Person prototype) {
		
		if(prototype != null) {
			
			for(MedicalRecord med : loadFile().getMedicalrecords()) {
				
				if(Objects.equals(med.getFirstName(), prototype.getFirstName()) 
						&& Objects.equals(med.getLastName(), prototype.getLastName())) {
					
					return getAgeWithBirthDate(med.getBirthdate());
				}
			}
		}
		return 0;
	}

	@Override
	public List<Person> getPersonByItsAddress(String address) {
		
		List<Person> listPers = new ArrayList<>();
		
		if(address != null) {
			
			for(Person pers : loadFile().getPersons()) {
				
				if(pers.getAddress().equals(address)) {
					
					listPers.add(pers);
				}
			}
			return listPers;
		}
		return listPers;
		
	}

	@Override
	public List<Person> getPersonByItsFirestationNumber(Integer station) {
		
		List<Person> listPers = new ArrayList<>();
		
		for(Person pers : loadFile().getPersons()) {
			
			if(getFirestationNumberByPersonsAddress(pers) == station) {
				
				listPers.add(pers);
			}
		}
		return listPers;
	}

	@Override
	public Integer getFirestationNumberByPersonsAddress(Person prototype) {
		
		if(prototype != null) {
			
			loadFile();
			
			return getFirestations().stream()
					.filter(station -> prototype.getAddress().equals(station.getAddress()))
					.findFirst().map(FireStation::getStation).orElse(0);
		}
		return 0;
	}


}


