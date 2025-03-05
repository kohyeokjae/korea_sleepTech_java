package phr_solution.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecordRequestDto {
	private Long patientId;
	private String dateOfVisit;	
	private String disagnosis;	
	private String treatment;	
}
