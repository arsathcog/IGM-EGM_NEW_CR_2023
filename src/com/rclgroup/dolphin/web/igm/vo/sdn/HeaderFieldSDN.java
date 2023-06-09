package com.rclgroup.dolphin.web.igm.vo.sdn;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;

public class HeaderFieldSDN {

	// Values come from "getSerialNumber()"
	private String senderID;
	private String receiverID;
	private String versionNo;
	private String indicator;
	private String messageID;
	private String sequenceOrControlNumber;
	private String date;
	private String time;
	private String reportingEvent;


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

	public String getSequenceOrControlNumber() {
		return sequenceOrControlNumber;
	}

	public void setSequenceOrControlNumber(String sequenceOrControlNumber) {
		sequenceOrControlNumber = FiledValidation.isNullAndSetlength(sequenceOrControlNumber, 8);
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
}