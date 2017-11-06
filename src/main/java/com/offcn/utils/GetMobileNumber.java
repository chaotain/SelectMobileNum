package com.offcn.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.offcn.dao.BaseDao;
import com.offcn.po.MobileNumber;

public class GetMobileNumber {
	public static void main(String[] args) {
		List<MobileNumber> list = getnumlist();
		insert(list);
	}

	public static List<MobileNumber> getnumlist(){
		String filename="C:\\Users\\Administrator\\Desktop\\µÚÎå½×¶Î\\day05\\Mobile.xls";
		List<MobileNumber>mlist=null;
		try {
			FileInputStream input = new FileInputStream(filename);
			Workbook workbook = WorkbookFactory.create(input);
			int sheets = workbook.getNumberOfSheets();
			mlist=new ArrayList<MobileNumber>();
			for(int i=0;i<sheets;i++){
				Sheet sheet = workbook.getSheetAt(i);
				int rownum = sheet.getPhysicalNumberOfRows();
				for(int j=0;j<rownum;j++){
					List<Object>list=new ArrayList<Object>();
					MobileNumber mobileNumber = new MobileNumber();
					Row row = sheet.getRow(j);
					int cellnum = row.getPhysicalNumberOfCells();
					for(int m=0;m<cellnum;m++){
						Cell cell = row.getCell(m);
						if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
								cell.getStringCellValue();
							}else if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
								cell.getNumericCellValue();
							}else if(cell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN){
								cell.getBooleanCellValue();
							}
						list.add(cell);
					}
					Object MobileNumber=list.get(1);
					Object MobileAreal = list.get(2);
					Object MobileType = list.get(3);
					Object AreaCode = list.get(4);
					Object PostCode = list.get(5);
					mobileNumber.setMobileNumber(Integer.parseInt((String.valueOf(MobileNumber))));
					mobileNumber.setMobileAreal((String.valueOf(MobileAreal)));
					mobileNumber.setMobileType((String.valueOf(MobileType)));
					mobileNumber.setAreaCode(Integer.parseInt((String.valueOf(AreaCode))));
					mobileNumber.setPostCode(Integer.parseInt((String.valueOf(PostCode))));
					mlist.add(mobileNumber);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mlist;
	}
	public static void insert(List<MobileNumber>list){
		for(MobileNumber num:list){
			int MobileNumber = num.getMobileNumber();
			String MobileAreal = num.getMobileAreal();
			String getMobileType = num.getMobileType();
			int AreaCode = num.getAreaCode();
			int PostCode = num.getPostCode();
			String sql="INSERT INTO mobilenumber(MobileNumber,MobileArea,MobileType,AreaCode,PostCode) VALUES("+MobileNumber+",'"+MobileAreal+"','"+getMobileType+"',"+AreaCode+","+PostCode+")";
			BaseDao.executeUpdate(sql);
		}
	}
}
