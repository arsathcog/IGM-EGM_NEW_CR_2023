package com.rclgroup.dolphin.web.igm.vo.sam;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;

public class PrsnDtlsSAM {
	
	private String prsnTypCdd;
	private String prsnFamilyName;
	private String prsnGivenName;
	private String prsnNatnltyCdd;
	private int psngrInTransitIndctr;
	private String crewmemberRankOrRatingName;
	private String crewmemberRankOrRatingCdd;
	private String psngrPrtOfEmbrktnCdd;
	private String psngrPrtOfEmbrktnName;
	private String psngrPrtOfDsmbrktnCdd;
	private String psngrPrtOfDsmbrktnName;
	private String prsnGenderCdd;
	private String prsnDtOfBirth;
	private String prsnPlaceOfBirthName;
	private String prsnCntryOfBirthCdd;

	public String getPrsnTypCdd() {
		return prsnTypCdd;
	}
	public void setPrsnTypCdd(String prsnTypCdd) {
		prsnTypCdd = FiledValidation.isNullAndSetlength(prsnTypCdd, 3);
		this.prsnTypCdd = prsnTypCdd;
	}
	public String getPrsnFamilyName() {
		return prsnFamilyName;
	}
	public void setPrsnFamilyName(String prsnFamilyName) {
		prsnFamilyName = FiledValidation.isNullAndSetlength(prsnFamilyName, 70);
		this.prsnFamilyName = prsnFamilyName;
	}
	public String getPrsnGivenName() {
		return prsnGivenName;
	}
	public void setPrsnGivenName(String prsnGivenName) {
		prsnGivenName = FiledValidation.isNullAndSetlength(prsnGivenName, 70);
		this.prsnGivenName = prsnGivenName;
	}
	public String getPrsnNatnltyCdd() {
		return prsnNatnltyCdd;
	}
	public void setPrsnNatnltyCdd(String prsnNatnltyCdd) {
		prsnNatnltyCdd = FiledValidation.isNullAndSetlength(prsnNatnltyCdd, 2);
		this.prsnNatnltyCdd = prsnNatnltyCdd;
	}
	
	public int getPsngrInTransitIndctr() {
		return psngrInTransitIndctr;
	}
	public void setPsngrInTransitIndctr(int psngrInTransitIndctr) {
		this.psngrInTransitIndctr = psngrInTransitIndctr;
	}
	
	public String getCrewmemberRankOrRatingCdd() {
		return crewmemberRankOrRatingCdd;
	}
	public void setCrewmemberRankOrRatingCdd(String crewmemberRankOrRatingCdd) {
		crewmemberRankOrRatingCdd = FiledValidation.isNullAndSetlength(crewmemberRankOrRatingCdd, 10);
		this.crewmemberRankOrRatingCdd = crewmemberRankOrRatingCdd;
	}
	public String getPsngrPrtOfEmbrktnCdd() {
		return psngrPrtOfEmbrktnCdd;
	}
	public void setPsngrPrtOfEmbrktnCdd(String psngrPrtOfEmbrktnCdd) {
		psngrPrtOfEmbrktnCdd = FiledValidation.isNullAndSetlength(psngrPrtOfEmbrktnCdd, 256);
		this.psngrPrtOfEmbrktnCdd = psngrPrtOfEmbrktnCdd;
	}
	public String getPsngrPrtOfDsmbrktnCdd() {
		return psngrPrtOfDsmbrktnCdd;
	}
	public void setPsngrPrtOfDsmbrktnCdd(String psngrPrtOfDsmbrktnCdd) {
		psngrPrtOfDsmbrktnCdd = FiledValidation.isNullAndSetlength(psngrPrtOfDsmbrktnCdd, 5);
		this.psngrPrtOfDsmbrktnCdd = psngrPrtOfDsmbrktnCdd;
	}
	public String getPrsnGenderCdd() {
		return prsnGenderCdd;
	}
	public void setPrsnGenderCdd(String prsnGenderCdd) {
		psngrPrtOfDsmbrktnCdd = FiledValidation.isNullAndSetlength(psngrPrtOfDsmbrktnCdd, 3);
		this.prsnGenderCdd = prsnGenderCdd;
	}
	public String getPrsnDtOfBirth() {
		return prsnDtOfBirth;
	}
	public void setPrsnDtOfBirth(String prsnDtOfBirth) {
		if ("".equals("prsnDtOfBirth")) {
			this.prsnDtOfBirth = prsnDtOfBirth;
		} else {
			if (prsnDtOfBirth.contains("/")) {
				String dateArray[] = prsnDtOfBirth.split("/");
				prsnDtOfBirth = dateArray[2] + dateArray[1] + dateArray[0];
				this.prsnDtOfBirth =prsnDtOfBirth;
			}else if(prsnDtOfBirth.contains("-")) {
				String dateArray[] = prsnDtOfBirth.split("-");
				prsnDtOfBirth = dateArray[2] + dateArray[1] + dateArray[0];
				this.prsnDtOfBirth =prsnDtOfBirth;
			}
		}
	}
	public String getPrsnPlaceOfBirthName() {
		return prsnPlaceOfBirthName;
	}
	public void setPrsnPlaceOfBirthName(String prsnPlaceOfBirthName) {
		prsnPlaceOfBirthName = FiledValidation.isNullAndSetlength(prsnPlaceOfBirthName, 35);
		this.prsnPlaceOfBirthName = prsnPlaceOfBirthName;
	}
	public String getPrsnCntryOfBirthCdd() {
		return prsnCntryOfBirthCdd;
	}
	public void setPrsnCntryOfBirthCdd(String prsnCntryOfBirthCdd) {
		prsnCntryOfBirthCdd = FiledValidation.isNullAndSetlength(prsnCntryOfBirthCdd, 2);
		this.prsnCntryOfBirthCdd = prsnCntryOfBirthCdd;
	}
	public String getCrewmemberRankOrRatingName() {
		return crewmemberRankOrRatingName;
	}
	public void setCrewmemberRankOrRatingName(String crewmemberRankOrRatingName) {
		this.crewmemberRankOrRatingName = crewmemberRankOrRatingName;
	}
	public String getPsngrPrtOfEmbrktnName() {
		return psngrPrtOfEmbrktnName;
	}
	public void setPsngrPrtOfEmbrktnName(String psngrPrtOfEmbrktnName) {
		this.psngrPrtOfEmbrktnName = psngrPrtOfEmbrktnName;
	}
	public String getPsngrPrtOfDsmbrktnName() {
		return psngrPrtOfDsmbrktnName;
	}
	public void setPsngrPrtOfDsmbrktnName(String psngrPrtOfDsmbrktnName) {
		this.psngrPrtOfDsmbrktnName = psngrPrtOfDsmbrktnName;
	}
	
	
}
