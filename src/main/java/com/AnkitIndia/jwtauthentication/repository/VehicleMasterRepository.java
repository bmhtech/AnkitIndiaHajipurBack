package com.AnkitIndia.jwtauthentication.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.AnkitIndia.jwtauthentication.model.VehicleMaster;
import com.AnkitIndia.jwtauthentication.model.Vehicle_weighment_load_unload;

public interface VehicleMasterRepository extends JpaRepository<VehicleMaster, Long>{
	
	@Query("SELECT MAX(SUBSTRING(vehicle_id , 4 , length(vehicle_id))) FROM VehicleMaster where vehicle_id like %:code% ")
	Optional<String> countId(@Param("code") String code);
	
	@Query( "select v from VehicleMaster v where v.modified_type = 'INSERTED' and v.vehicle_id =:vid ")
	Optional<VehicleMaster> getVehicleDtls(@Param("vid") String vid);
	
	@Query( "select v from VehicleMaster v where v.modified_type = 'INSERTED' ORDER BY v.vehicle_id DESC")
	List<VehicleMaster> findAllVehicles();
	
	@Query(value= "select * from vehicle_master v where v.modified_type = 'INSERTED' ORDER BY v.vehicle_id DESC", nativeQuery=true)
	List<Map<String, Object>> VehicleNoNew();
	
	@Query("SELECT v FROM VehicleMaster v WHERE CONCAT(v.vehicle_no, v.vehtype_code, v.tareweight_uom, v.onwer_name, v.onwer_address,v.tareweight_uomname,v.vehtype_name) LIKE %:keyword% and v.modified_type = 'INSERTED' ORDER BY v.vehicle_id DESC")
    public List<VehicleMaster> findVehicles(String keyword);
	
	@Query( "select v from VehicleMaster v where v.modified_type = 'INSERTED' ")
	List<VehicleMaster> getVehicleNameCode();
	
	@Query( "select v from VehicleMaster v where v.modified_type = 'INSERTED' and v.vehicle_id =:vid ")
	VehicleMaster getVehicleDetails(@Param("vid") String vid);
	
	@Query( "select v from VehicleMaster v where v.modified_type = 'INSERTED' and v.vehicle_no =:vid ")
	VehicleMaster getVehicleDetailsVno(@Param("vid") String vid);
	
	@Query( "select v from VehicleMaster v where v.modified_type = 'INSERTED' and v.vehicle_id =:vid ")
	List<VehicleMaster> getVehicleList(@Param("vid") String vid);
	
	@Query( "select v from VehicleMaster v where v.modified_type = 'INSERTED' and v.vehicle_id =:vid and v.weighment_status =:status")
	List<VehicleMaster> getVehicleWithoutWt1(@Param("vid") String vid,@Param("status") boolean status);
	
	@Query( "select v from VehicleMaster v where v.modified_type = 'INSERTED' and v.vehicle_id =:vid")
	List<VehicleMaster> getVehicleWithoutWt2(@Param("vid") String vid);
	
	@Query( "select v from VehicleMaster v where v.modified_type = 'INSERTED' and v.vehtype_code =:tcode ")
	List<VehicleMaster> getVehicleNumberByVtype(@Param("tcode") String tcode);
	
	@Query( "select v from VehicleMaster v where v.modified_type = 'INSERTED' and v.vehicle_id =:vcode ")
	List<VehicleMaster> getVehicleTypeName(@Param("vcode") String vcode);
	
	@Query( "select v from VehicleMaster v where v.modified_type = 'INSERTED' and v.weighment_status =:status")
	List<VehicleMaster> getVehicles(@Param("status") boolean status);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE VehicleMaster v SET v.weighment_status =:status WHERE v.vehicle_id =:vid")
	int updateStatus(@Param("vid") String vid,@Param("status") boolean status);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE VehicleMaster v SET v.weighment_vehicle =:stat WHERE v.vehicle_id =:vid")
	int updateVehicleForWeighment(@Param("vid") String vid,@Param("stat") int stat);
	
	@Query("select max(substring(vehicle_no , 8, length(vehicle_no))) from VehicleMaster where vehicle_no like %:code% and company_id =:company ")
	String getVehicleSequenceId(@Param("code") String code,@Param("company") String company);
	
/*	@Query(value = "{call checkVehicleMasterUsage(:code)}", nativeQuery = true)
	String checkVehicleMasterUsage(@Param("code") String code);*/
	
	@Query("select b.vehicle_no from VehicleMaster b where b.modified_type = 'INSERTED' and b.vehicle_no =:sno")
	String chkVehicleNoStatus(@Param("sno") String sno);
    
	@Query( "select v from Vehicle_weighment_load_unload v where v.modified_type = 'INSERTED' and (v.ref_name='Unload Advice' OR v.ref_name='Goods Stock Transfer') and (v.ref_name_type='Stock Transfer' OR v.ref_name_type='Goods Stock Transfer') and v.stock_grn_status=0  ")
	List<Vehicle_weighment_load_unload> getVehiclesFromVehicleLoadUnload();
	
	@Query( "select v from Vehicle_weighment_load_unload v where v.modified_type = 'INSERTED' and (v.ref_name='Unload Advice' OR v.ref_name='Goods Stock Transfer') and (v.ref_name_type='Stock Transfer' OR v.ref_name_type='Goods Stock Transfer') and v.stock_grn_status=0 and v.vehicle_id=:vehicleid")
	Vehicle_weighment_load_unload getRefNameType(@Param("vehicleid") String vehicleid);
	
	@Modifying(clearAutomatically = true)
	@Query("DELETE from Vehicle_weighment_load_unload v  WHERE v.reference_id =:refid")
	int resetGoodsTransferChallanVehicle(@Param("refid") String refid);
	
	
	
	@Query(value="select * from vehicle_master v where v.modified_type = 'INSERTED' and v.weighment_status='0'",nativeQuery=true)
	 List<Map<String,Object>> findvechilethroughstatus();
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Vehicle_other_weighment_load_unload v SET v.weighment_status =:stat WHERE v.vehicle_id =:vid")
	int updateVehicleOtherLoadUnload(@Param("vid") String vid,@Param("stat") int stat);
	
	@Query(value="select * from vehicle_master where modified_type='INSERTED'",nativeQuery=true)
	 List<Map<String,Object>> allVechileList();
}
