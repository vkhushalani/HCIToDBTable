package HCIToTable.rest.services.service;

import java.util.List;

import HCIToTable.rest.services.model.RBPEmployees;

public interface RBPEmployeesService {
	
	public List<RBPEmployees> findAll();
	public RBPEmployees update(RBPEmployees employee);
	public RBPEmployees create(RBPEmployees employee);
	public RBPEmployees findById(Long id);
	public RBPEmployees findByKey(Integer grp,String uId);
	public void deleteById(Long id);
	public void deleteByObject(RBPEmployees employee);
	public int updateAll();
	public int deleteFlagged();
	public int deleteTodayFlagged();
}
