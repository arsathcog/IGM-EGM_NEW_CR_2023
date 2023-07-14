package com.rclgroup.dolphin.web.igm.vo.sam;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rclgroup.dolphin.web.igm.vo.FiledValidation;

@JsonPropertyOrder({"senderID","receiverID","versionNo","indicator","messageID","sequenceOrControlNumber"
	,"date","time","reportingEvent"})

public class HeaderFieldSAM {

	private String senderID;
	private String receiverID;
	private String versionNo;
	private String indicator;
	private String messageID;
	private int sequenceOrControlNumber;
	private String date;
	private String time;
	private String reportingEvent;
	
    private Map<String, Object> data;
    
    public HeaderFieldSAM() {
        data = new LinkedHashMap<>();
    }
    
    public void addField(String key, Object value) {
        data.put(key, value);
    }

    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(data);
    }

	

	public String getSenderID() {
		return senderID;
	}

	public void setSenderID(String senderID) {
		senderID = FiledValidation.isNullAndSetlength(senderID, 30);
		this.senderID = senderID;
	}

	public String getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(String receiverID) {
		receiverID = FiledValidation.isNullAndSetlength(receiverID, 30);
		this.receiverID = receiverID;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		versionNo = FiledValidation.isNullAndSetlength(versionNo, 7);
		this.versionNo = versionNo;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		indicator = FiledValidation.isNullAndSetlength(indicator, 1);
		this.indicator = indicator;
	}

	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		messageID = FiledValidation.isNullAndSetlength(messageID, 7);
		this.messageID = messageID;
	}



	public int getSequenceOrControlNumber() {
		return sequenceOrControlNumber;
	}

	public void setSequenceOrControlNumber(int sequenceOrControlNumber) {
		this.sequenceOrControlNumber = sequenceOrControlNumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		if (date == null || date == "") {
			this.date = date;
		} else {
			if (date.contains("/")) {
				String dateArray[] = date.split("/");
				date = dateArray[2] + dateArray[1] + dateArray[0];
				this.date = date;
			}
			this.date = date;
		}
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getReportingEvent() {
		return reportingEvent;
	}

	public void setReportingEvent(String reportingEvent) {
		reportingEvent = FiledValidation.isNullAndSetlength(reportingEvent, 4);
		this.reportingEvent = reportingEvent;
	}

	@Override
	public String toString() {
		return "HeaderFieldSAM [senderID=" + senderID + ", receiverID=" + receiverID + ", versionNo=" + versionNo
				+ ", indicator=" + indicator + ", messageID=" + messageID + ", sequenceOrControlNumber="
				+ sequenceOrControlNumber + ", date=" + date + ", time=" + time + ", reportingEvent=" + reportingEvent
				+ "]";
	}

	
	
}