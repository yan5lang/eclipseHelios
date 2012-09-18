/**
 * %document warehouse%
 * %2.0%
 */
package com.morningstar.documentwarehouse.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import javax.activation.DataHandler;
import javax.activation.DataSource;

import org.apache.axiom.attachments.ByteArrayDataSource;
import org.apache.axis2.AxisFault;

import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.AddDocument;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.AddDocumentResponse;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.AddLargeDocument;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.AddLargeDocumentResponse;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.ArrayOfInt;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.DocumentMetaInfo;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.GetBigDocumentData;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.GetBigDocumentDataResponse;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.GetDocumentData;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.GetDocumentDataResponse;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.GetDocumentIdList;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.GetDocumentIdListResponse;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.GetDocumentMetaInfo;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.GetDocumentMetaInfoList;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.GetDocumentMetaInfoListResponse;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.GetDocumentMetaInfoResponse;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.UpdateDocument;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.UpdateDocumentMetaInfo;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.UpdateDocumentMetaInfoResponse;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.UpdateDocumentResponse;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.UpdateLargeDocumentData;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.UpdateLargeDocumentDataResponse;


/**
 * webservice api wrapper
 */
public class DocumentClientAPI {
	
	// store cookies for flowing api calls 
	private String cookies; // = "SessionHeader=0349F4AD597C10F656D84FA792D3CB58C8D7BCBCD32B21F7EFD58E26F4865EE8B2B6C8F56A9E534EE1DB20587B712D220EE20BBAEE205D5D55A0E1C7AEB6CE098DCCBA9D3952D31089AB5E7ADBEFA578ACF7C5F6054040FDA92353A210E10BC5E83F9D41E374CDC230A1BD49F6AF8EA25D004B1DE03D9E2C3C0030167FF0CA344C435E07B61649F43D6BB6D2E43DB7326500379A037B8D5BFD6CF114047798543F5C4A4108D0729FF591417855520DAA49D680CB236E54A86B8CF1A35AACA5D396D24775BF4AFFF8FC9F1D6BC10C82B52AC2BCBF3E6862F5AD66A70BC2954C15E7EFA2721647698419F59FAE977284113573717FAF761E1633729FA0BC59CFF893DEB23F4D51DF5B3C9A21350BFECCA5B39F2833CECA1C68672BD9BC5A607D72A142C458B3F0B8AF0616A36B51101441DE91C4F63C1DFD0356FFABB06807E661399B2004761B939934F504635124D17F1B4E991DCF8F29E7953A3BB68A249D459C435232D9E900C7CBD27A7103847E6D9F962B22F103B78DC8641B9069482C448E6553CFC68A2E0A56D84FA792D3CB58C8D7BCBCD32B21F7EFD58E26F4865EE8B2B6C8F56A9E534EE1DB20587B712D220EE20BBAEE205D5D666EFE0A75C33C7E";
	// api call timeout
	// for the large file, 
	// we can set timeout longer
	private long timeout = 20000;
	// 5M
	private int downloadBufferSize = 1024 * 1024;
	private int uploadBufferSize = 1024 * 1024;
	// 
	DocumentAPIDocumentAPISoap12Stub stub;
	
	public DocumentClientAPI(String targetEndpoint, String email, String password, String loginUrl) 
			throws DWClientAPIException {
		super();
		init(targetEndpoint);
		login(loginUrl, email, password);
	}

	// initialize
	private void init(String targetEndpoint) throws DWClientAPIException {
		try {
			stub = new DocumentAPIDocumentAPISoap12Stub(targetEndpoint);						
	
		} catch (AxisFault e) {
			throw new DWClientAPIException("Initialize Excepion: "+e.getMessage());
			
		}
	}
	
	// login to document warehouse, and return access token 
	public String login(String loginUrl, String email, String password) throws DWClientAPIException {
		try {
			java.net.URLConnection conn = new java.net.URL(loginUrl).openConnection();
			HttpURLConnection httpConn = (HttpURLConnection)conn;
			httpConn.setRequestMethod("POST");
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			httpConn.setInstanceFollowRedirects(false);
			
			byte[] inputByte = ("email=" + email +"&password=" + password).getBytes("utf-8");
	        httpConn.addRequestProperty("content-length", String.valueOf(inputByte.length));
			OutputStream outputStream = httpConn.getOutputStream();		
			outputStream.write(inputByte);	
	
			httpConn.connect();
	
			//get cookies from header       
			CookieManagement cm = new CookieManagement(httpConn);
			String[] cookieStrings = cm.getCookieStrings();
			httpConn.disconnect();
			
			String result = "";
			if(cookieStrings!=null) {
				for(int i=0; i<cookieStrings.length; i++)
				{
					//result += cookieStrings[i].substring(0, cookieStrings[i].indexOf(";")) + ";";
					result += cookieStrings[i] + ";";
				}
				//remove last ";"
				result = result.substring(0, result.length()-1);
				// store cookies for flowing api calls 
				cookies = result;
			}
			else {
				throw new DWClientAPIException("login failed");
			}
			return result;
		} catch (Exception ex) {
			throw new DWClientAPIException("Login Exception", ex);
		}	
		
	}
	
	// set Options, now including Cookies and Timeout
	private void setOptions() throws DWClientAPIException {
		if (cookies==null)
			throw new DWClientAPIException("Access Key invalid, Please login again.");
		stub._getServiceClient().getOptions().setProperty(
				org.apache.axis2.transport.http.HTTPConstants.HEADER_COOKIE, 
					cookies);
		stub._getServiceClient().getOptions().setManageSession(true);
		stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeout);
	}
	
	//
	public int[] getDocumentIdList(int documentGroup, String startDate, String endDate) 
					throws DWClientAPIException {
		// 
		setOptions();
		try {
			GetDocumentIdList 
				getDocumentIdList224 = (GetDocumentIdList)getStubObject(GetDocumentIdList.class);
			getDocumentIdList224.setDocumentGroup(documentGroup);
			getDocumentIdList224.setStartDateTime(startDate);
			getDocumentIdList224.setEndDateTime(endDate);
			GetDocumentIdListResponse rsp = stub.getDocumentIdList(getDocumentIdList224);
			ArrayOfInt ai = rsp.getGetDocumentIdListResult();
			return ai.get_int();
		} catch (Exception e) {
			throw new DWClientAPIException("getDocumentIdList Exception: "+e.getMessage());
		}
		
	}

	//
	public DocumentMetaInfo getDocumentMetaInfo(int	documentId) throws DWClientAPIException {
		setOptions();
		try {
			GetDocumentMetaInfo getDocumentMetaInfo226 = 
				(GetDocumentMetaInfo)getStubObject(GetDocumentMetaInfo.class);
			getDocumentMetaInfo226.setDocumentId(documentId);
			GetDocumentMetaInfoResponse rsp = stub.getDocumentMetaInfo(getDocumentMetaInfo226);
			return rsp.getGetDocumentMetaInfoResult();
		} catch (Exception e) {
			throw new DWClientAPIException("getDocumentMetaInfo Exception: "+e.getMessage());
		}

	}
	
	//
	public DocumentMetaInfo[] getDocumentMetaInfoList(int documentGroup, String startDate, String endDate) 
					throws DWClientAPIException {
		setOptions();
		try {
			GetDocumentMetaInfoList getDocumentMetaInfoList248 = 
				(GetDocumentMetaInfoList)getStubObject(GetDocumentMetaInfoList.class);
			getDocumentMetaInfoList248.setDocumentGroup(documentGroup);
			getDocumentMetaInfoList248.setStartDateTime(startDate);
			getDocumentMetaInfoList248.setEndDateTime(endDate);
			GetDocumentMetaInfoListResponse rsp = stub.getDocumentMetaInfoList(getDocumentMetaInfoList248);
			return rsp.getGetDocumentMetaInfoListResult().getDocumentMetaInfo();
		} catch (Exception e) {
			throw new DWClientAPIException("getDocumentMetaInfoList Exception: "+e.getMessage());
		}

	}

	// 
	public void downloadDocument(DocumentMetaInfo docMetaInfo, String fileSavePath) 
					throws DWClientAPIException {
		setOptions();
		//
		int fileSize = docMetaInfo.getFileSize();
		if(fileSize >= downloadBufferSize){
			getBigDocumentData(docMetaInfo, fileSavePath);
		}
		else {
			getDocumentData(docMetaInfo, fileSavePath);
		}		
		//verify hash code
		try {
			String localFileHashCode = Util.GenerateSHA1HashCode(fileSavePath);
			String serverFileHashCode = docMetaInfo.getFileSHA1HashCode();		
			if(serverFileHashCode!=null 
					&& serverFileHashCode.length() > 0 
						&& !localFileHashCode.equalsIgnoreCase(serverFileHashCode)) {
				throw new Exception("Hash code not match. File is not same with the one in document warehouse. Please re-download the file.");
			}
		} catch (Exception e) {
			throw new DWClientAPIException("downloadDocument Exception: "+e.getMessage());
		}
		
	}
	
	//
	private void getDocumentData(DocumentMetaInfo docMetaInfo, String fileSavePath) throws DWClientAPIException {
		setOptions();
		try {
			GetDocumentData getDocumentData250 = 
					(GetDocumentData)getStubObject(GetDocumentData.class);
			getDocumentData250.setDocumentId(docMetaInfo.getDocumentId());
			GetDocumentDataResponse rsp = stub.getDocumentData(getDocumentData250);
			DataHandler dh = rsp.getGetDocumentDataResult();
			File f = new File(fileSavePath);
			FileOutputStream fos = new FileOutputStream(f);
			dh.writeTo(fos);
			fos.close();
		} catch (Exception e) {
			throw new DWClientAPIException("getDocumentData Exception: "+e.getMessage());
		}
		
	}
	
	//
	private void getBigDocumentData(DocumentMetaInfo docMetaInfo, String fileSavePath) 
		throws DWClientAPIException {
		setOptions();
		try {
			GetBigDocumentData getBigDocumentData230 = 
				(GetBigDocumentData)getStubObject(GetBigDocumentData.class);
			
			int offset = 0;
			int sizeToRead = this.downloadBufferSize;
			File f = new File(fileSavePath);
			if(f.exists())
				f.delete();
			
			while(true) {
				getBigDocumentData230.setDocumentId(docMetaInfo.getDocumentId());
				getBigDocumentData230.setOffset(offset);
				getBigDocumentData230.setBufferSize(sizeToRead);
				
				GetBigDocumentDataResponse rsp = 
					stub.getBigDocumentData(getBigDocumentData230);
				DataHandler dh = rsp.getGetBigDocumentDataResult();
				if((dh == null) || (dh.getInputStream().available()==0)) {				
					break;
				}
				
				InputStream is = dh.getInputStream();		
				byte[] buffer = new byte[sizeToRead];
				int readLenght = 0;			
				FileOutputStream fos = new FileOutputStream(f, true);
				while((readLenght = is.read(buffer, 0, buffer.length)) >0) {
					fos.write(buffer, 0, readLenght);					
					offset += readLenght;
				}
				fos.close();
			}
		} catch(Exception e) {
			throw new DWClientAPIException("GetDocumentData Exception: "+e.getMessage());
		}

	}
	
	//
	public DocumentMetaInfo uploadNewDocument (
            String      fileName, 
            String      fileFormat, 
            int         documentGroup, 
            String      fileCreationDate, 
            String      fileModifiedDate,
            String      documentMeta,
            int[]       documentAttributeIds,
            File file) throws DWClientAPIException {
		setOptions();
		// meta info returned
		DocumentMetaInfo docMetaInfo=null;
		try {
			if(file.length()<uploadBufferSize) {
		  		byte[] bytes = new byte[(int)file.length()];
				FileInputStream fis = new FileInputStream(file);
				fis.read(bytes);
				fis.close();
				docMetaInfo = addDocument(
						        fileName, fileFormat, documentGroup, fileCreationDate, 
								fileModifiedDate, documentMeta, documentAttributeIds, bytes);
	    	}
	    	else {
	    		docMetaInfo = addLargeDocument(fileName, fileFormat, documentGroup, fileCreationDate, fileModifiedDate, documentMeta, documentAttributeIds, file);
	    	}    	
	    	// verify hash code
	        String localFileSHA1HashCode = Util.GenerateSHA1HashCode(file);        
	        if (!localFileSHA1HashCode.equalsIgnoreCase(docMetaInfo.getFileSHA1HashCode())) {
	            throw new Exception("File uploaded failed, file hash code not match");
	        }
			return docMetaInfo;
		} catch(Exception e) {
			throw new DWClientAPIException("UploadDocument Exception: "+e.getMessage());
		}
	}
	
	//
	private DocumentMetaInfo addDocument(
			String      fileName, 
            String      fileFormat, 
            int         documentGroup, 
            String      fileCreationDate, 
            String      fileModifiedDate,
            String      documentMeta,
            int[]       documentAttributeIds,
            byte[] bytes) throws DWClientAPIException {		
		setOptions();
		try {
			AddDocument addDocument244 = (AddDocument)getStubObject(AddDocument.class);
			//
			addDocument244.setFileName(fileName);
			addDocument244.setFileFormat(fileFormat);
			addDocument244.setDocumentGroup(documentGroup);
			addDocument244.setFileCreationDate(fileCreationDate);
			addDocument244.setFileModifiedDate(fileModifiedDate);
			addDocument244.setDocumentMeta(documentMeta);
			
			ArrayOfInt attributeIds = new ArrayOfInt();
			attributeIds.set_int(documentAttributeIds);		
			addDocument244.setDocumentAttributeIds(attributeIds);
			
			DataSource dataSource  = new ByteArrayDataSource(bytes);		
			javax.activation.DataHandler data = new javax.activation.DataHandler(dataSource);
			addDocument244.setDocumentData(data);
				
			AddDocumentResponse rsp = stub.addDocument(addDocument244);
			DocumentMetaInfo docMetaInfo = rsp.getAddDocumentResult();
			return docMetaInfo;
		} catch(Exception e) {
			throw new DWClientAPIException("addDocument Exception: "+e.getMessage());
		}

	}
	
	//
	private DocumentMetaInfo addLargeDocument(
			String      fileName, 
            String      fileFormat, 
            int         documentGroup, 
            String      fileCreationDate, 
            String      fileModifiedDate,
            String      documentMeta,
            int[]       documentAttributeIds,
            File file) throws DWClientAPIException {
		setOptions();
		try {
		    
			int offset = 0;
			int bufferSize = this.uploadBufferSize;
			long fileSize = file.length();
			FileInputStream fis = new FileInputStream(file);
			// 
			AddLargeDocumentResponse rsp = null;
			UpdateLargeDocumentData uld = (UpdateLargeDocumentData)getStubObject(UpdateLargeDocumentData.class);
			boolean exit = false;

			while(!exit) {
				//it's last round
				if(fileSize - (offset + bufferSize) < 0)
				{
					bufferSize = (int)(fileSize - offset);
					exit = true;
				}
				
				byte[] bytes = new byte[bufferSize];
				int lengthRead = fis.read(bytes, 0, bufferSize);
				
				//first round
				if(offset==0) {
					AddLargeDocument ald = (AddLargeDocument)getStubObject(AddLargeDocument.class);
					ald.setFileName(fileName);
					ald.setFileCreationDate(fileCreationDate);
					ald.setFileModifiedDate(fileModifiedDate);
					ald.setFileFormat(fileFormat);
					ald.setDocumentMeta(documentMeta);
					//
					ArrayOfInt attributeIds = new ArrayOfInt();
					attributeIds.set_int(documentAttributeIds);	
					ald.setDocumentAttributeIds(attributeIds);
					ald.setDocumentGroup(documentGroup);
					//
					DataSource dataSource  = new ByteArrayDataSource(bytes);		
					javax.activation.DataHandler dh = new javax.activation.DataHandler(dataSource);
					ald.setDocumentData(dh);									
					rsp = stub.addLargeDocument(ald); 					
				}
				else {
					uld.setDocumentId(rsp.getAddLargeDocumentResult().getDocumentId());
					uld.setOffset(offset);
					uld.setIsEOF(exit);

					DataSource dataSource = new ByteArrayDataSource(bytes);
					javax.activation.DataHandler dh = new javax.activation.DataHandler(dataSource);
					uld.setDocumentData(dh);				
					
					stub.updateLargeDocumentData(uld);		
				}
				
				offset += lengthRead;			
			}		
			fis.close();			
			return getDocumentMetaInfo(rsp.getAddLargeDocumentResult().getDocumentId());			
		} catch(Exception e) {
			throw new DWClientAPIException("addLargeDocument Exception: "+e.getMessage());
		}

	}	
	
	// note:
	// update the file data and meta info at same time
	public DocumentMetaInfo updateDocument(
    		int			documentId,
    		String      fileName, 
            String      fileFormat, 
            int         documentGroup, 
            String      fileCreationDate, 
            String      fileModifiedDate,
            String      documentMeta,
            int[]       documentAttributeIds,
            File		file
            ) throws DWClientAPIException {
		setOptions();
		try {
			// check if the file is the same as server. 
			// only upload file when file hash is not same.
	    	String localFileHash = Util.GenerateSHA1HashCode(file);
	    	DocumentMetaInfo docMetaInfo = getDocumentMetaInfo(documentId);	 
	    	// upload a file which is same with server
	    	// update the meta info only
	    	if(docMetaInfo.getFileSHA1HashCode().equalsIgnoreCase(localFileHash)) {	    	
	    		updateDocumentMetaInfo(documentId, fileName, fileFormat,
	    				documentGroup, fileCreationDate, fileModifiedDate,
	    				documentMeta, documentAttributeIds);
	    		System.out.println("You'r trying to upload a file which is then same as server's. " +
				"Update the meta info only.");
	    		// updata meta data
				return updateDocumentMetaInfo(documentId, fileName, 
		    		            fileFormat, documentGroup, 
		    		            fileCreationDate, fileModifiedDate,
		    		            documentMeta, documentAttributeIds);
	    		
	    	} else { 
	    		if(file.length()<uploadBufferSize) {
	    			return updateSmallDocument(
	    					documentId, fileName, 
	    		            fileFormat, documentGroup, 
	    		            fileCreationDate, fileModifiedDate,
	    		            documentMeta, documentAttributeIds,
	    		            file, localFileHash);
	    		} else {
	    			return updateLargeDocument(
	    					documentId, fileName, 
	    		            fileFormat, documentGroup, 
	    		            fileCreationDate, fileModifiedDate,
	    		            documentMeta, documentAttributeIds,
	    		            file, localFileHash);
	    		}
	    	}	    	
		} catch(Exception e) {
			throw new DWClientAPIException("updateDocument Exception: "+e.getMessage());
		} 
		
  	}

	// update Document that data size less than uploadBufferSize(5M)
	private DocumentMetaInfo updateSmallDocument(
    		int			documentId,
    		String      fileName, 
            String      fileFormat, 
            int         documentGroup, 
            String      fileCreationDate, 
            String      fileModifiedDate,
            String      documentMeta,
            int[]       documentAttributeIds,
            File		file,
            String      fileHash
            ) throws DWClientAPIException {
		
		//
		FileInputStream fis = null;
		try {
			// load the file
		    fis	= new FileInputStream(file);
		    //
			UpdateDocument ud = (UpdateDocument)getStubObject(UpdateDocument.class);
			// meta info
			ud.setDocumentId(documentId);
			ud.setFileName(fileFormat);
			ud.setFileFormat(fileFormat);
			ud.setDocumentGroup(documentGroup);
			ud.setFileCreationDate(fileCreationDate);
			ud.setFileModifiedDate(fileModifiedDate);
			ud.setDocumentMeta(documentMeta);
			// 
			ArrayOfInt attributeIds = new ArrayOfInt();
			attributeIds.set_int(documentAttributeIds);		
			ud.setDocumentAttributeIds(attributeIds);			
	    	// set file data
			// read data from file
			byte[] bytes = new byte[(int)file.length()];	
			fis.read(bytes, 0, (int)file.length());
    		//
			DataSource ds;
			ds = new ByteArrayDataSource(bytes);
			javax.activation.DataHandler dh;
			dh = new javax.activation.DataHandler(ds);
			ud.setDocumentData(dh);			
			// go, update
			UpdateDocumentResponse rsp = stub.updateDocument(ud);
			// get the meta info of the new updated file, and  verify hash code
			DocumentMetaInfo docMetaInfo = getDocumentMetaInfo(documentId);
	    	if(!docMetaInfo.getFileSHA1HashCode().equalsIgnoreCase(fileHash)) {
	            throw new Exception("File updated failed, file hash code not match");    		
	    	}
			return rsp.getUpdateDocumentResult();
		} catch(Exception e) {
			throw new DWClientAPIException("updateDocument Exception: "+e.getMessage());
		} finally {
    		// Don't forget to close the file stream
			if (fis!=null)
				try {
					fis.close();
				} catch (IOException e) {
					
				}
    	}
	}
	
	// update Document that data size bigger and equal than uploadBufferSize(5M)
	private DocumentMetaInfo updateLargeDocument(
    		int			documentId,
    		String      fileName, 
            String      fileFormat, 
            int         documentGroup, 
            String      fileCreationDate, 
            String      fileModifiedDate,
            String      documentMeta,
            int[]       documentAttributeIds,
            File		file,
            String      fileHash
            ) throws DWClientAPIException {
		setOptions();
		//
		FileInputStream fis = null;
		try {
			// load the file
		    fis	= new FileInputStream(file);
			// update file data first
			int offset = 0;
			int bufferSize = uploadBufferSize;
			long fileSize = file.length();
			boolean exit = false;
			UpdateLargeDocumentData uld = 
				(UpdateLargeDocumentData)getStubObject(UpdateLargeDocumentData.class);		    		
			while(!exit) {
				// is it the last round
				if(fileSize - (offset + bufferSize) < 0) {
					bufferSize = (int)(fileSize - offset);
					exit = true;
				}		    			
				byte[] bytes = new byte[bufferSize];
				int lengthRead = fis.read(bytes, 0, bufferSize);
				//
				uld.setDocumentId(documentId);
				uld.setOffset(offset);
				uld.setIsEOF(exit);
				// set file data
				DataSource ds;
				ds = new ByteArrayDataSource(bytes);
				javax.activation.DataHandler dh;
				dh = new javax.activation.DataHandler(ds);
				uld.setDocumentData(dh);
				//
				UpdateLargeDocumentDataResponse rsp = stub.updateLargeDocumentData(uld);
				offset += lengthRead;
			}		
			// get the meta info of the new updated file, and  verify hash code
			DocumentMetaInfo docMetaInfo = getDocumentMetaInfo(documentId);
	    	if(!docMetaInfo.getFileSHA1HashCode().equalsIgnoreCase(fileHash)) {
	            throw new Exception("File updated failed, file hash code not match");    		
	    	}
			// updata meta data
			return updateDocumentMetaInfo(documentId, fileName, 
	    		            fileFormat, documentGroup, 
	    		            fileCreationDate, fileModifiedDate,
	    		            documentMeta, documentAttributeIds);
		} catch(Exception e) {
			throw new DWClientAPIException("updateDocument Exception: "+e.getMessage());
		} finally {
    		// Don't forget to close the file stream
			if (fis!=null)
				try {
					fis.close();
				} catch (IOException e) {
					
				}
    	}
	} 
	
	/**
	 * notes:
	 * this api updates the meta info only, but not including file format
	 * if file data is needed to be updated at the same time, 
	 * use updateDocument instead 
	 */
	public DocumentMetaInfo updateDocumentMetaInfo(
			int			documentId,
    		String      fileName, 
            String      fileFormat, 
            int         documentGroup, 
            String      fileCreationDate, 
            String      fileModifiedDate,
            String      documentMeta,
            int[]       documentAttributeIds) throws DWClientAPIException {
		setOptions();
		try {
			UpdateDocumentMetaInfo input = (UpdateDocumentMetaInfo)getStubObject(UpdateDocumentMetaInfo.class);
			input.setDocumentId(documentId);
			input.setFileName(fileName);
			input.setFileFormat(fileFormat);
			input.setDocumentGroup(documentGroup);
			input.setFileCreationDate(fileCreationDate);
			input.setFileModifiedDate(fileModifiedDate);
			input.setDocumentMeta(documentMeta);
            //
			UpdateDocumentMetaInfoResponse rsp = stub.updateDocumentMetaInfo(input);
			return rsp.getUpdateDocumentMetaInfoResult();
		} catch(Exception e) {
			throw new DWClientAPIException("updateDocumentMetaInfo Exception: "+e.getMessage());
		}

	}
	
	// Create an ADBBean and provide it as the test object
	private org.apache.axis2.databinding.ADBBean getStubObject(
			java.lang.Class type) throws java.lang.Exception {
		return (org.apache.axis2.databinding.ADBBean) type.newInstance();
	}
	
	public long getTimeOut() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	
	public void setDownloadBufferSize(int size)
	{
		this.downloadBufferSize = size;
	}
	
	public void setUploadBufferSize(int size)
	{
		this.uploadBufferSize = size;
	}
	
}
