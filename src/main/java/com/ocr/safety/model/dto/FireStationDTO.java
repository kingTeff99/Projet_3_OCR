package com.ocr.safety.model.dto;

import com.ocr.safety.model.FireStation;

public class FireStationDTO {
	
		private Integer stationId;
		
		private String address;
		
		public FireStationDTO() {
			
		}

		public FireStationDTO(FireStation firestation) {
			this.setStationId(firestation.getStation());
			this.setAddress(firestation.getAddress());
		}

		public Integer getStationId() {
			return stationId;
		}

		public void setStationId(Integer stationId) {
			this.stationId = stationId;
		}
		
		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		@Override
		public String toString() {
			return "FireStationDTO [stationId=" + stationId + ", address=" + address + "]";
		}
		
		


}
