package phr_solution.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecordResponseDto {
	private Long id;
	private Long patientId;
	private String dateOfVisit;	
	private String disagnosis;	
	private String treatment;	
	private Date inquiryTiem; // 건강 기록 조회 시간
}
