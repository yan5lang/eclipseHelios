package demo;

import java.io.File;

import com.morningstar.documentwarehouse.api.DocumentClientAPI;
import com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.DocumentMetaInfo;

public class SampleCode {
	private DocumentClientAPI documentAPI = null;

	public SampleCode() throws Exception {
		// API initialize
		String targetEndpoint = "http://dev.datamanager.morningstar.com:8080/docwh/services/DocumentAPI";
		String email = "MagicDoor@morningstar.com";
		String password = "It'sM@g!c";
		String loginUrl = "http://dev.datamanager.morningstar.com:8080/docwh/account/dologinApi.action";
		documentAPI = new DocumentClientAPI(targetEndpoint, email, password,
				loginUrl);

		/**
		 * Set upload or download buffer size. 
		 * By default, the buffer size is 5M bytes.
		 * Please set it to a proper value according to your JVM allocated
		 * memory size and network bandwidth.
		 */
		documentAPI.setDownloadBufferSize(15 * 1024 * 1024);
		documentAPI.setUploadBufferSize(15 * 1024 * 1024);
		// set http request time out to longer for large file downloading or uploading
		//documentAPI.setTimeout(200000);
	}

	public void downloadDocuments(String fileDownloadFolder) {
		try {
			// Get all file information from documentGroup 1 (fund documents),
			// which was added or updated between 8:00 and 20:00 on 2010-01-21
			// Beijing China time.
			int documentGroup = 99;
			String startDate = "2011-01-25T08:00:00+08:00";
			String endDate = "2011-01-25T20:00:00+08:00";
			DocumentMetaInfo[] docMetaInfoList = documentAPI
					.getDocumentMetaInfoList(documentGroup, startDate, endDate);

			// Download files.
			for (int i = 0; i < docMetaInfoList.length; i++) {
				try {
					// Get document meta information
					int documentId = docMetaInfoList[i].getDocumentId();
					int fileSize = docMetaInfoList[i].getFileSize();
					String hashCode = docMetaInfoList[i].getFileSHA1HashCode();
					// Print meta info to console
					System.out.println("DocumentId: " + documentId);
					System.out.println("File Size: " + fileSize);
					System.out.println("SHA1 hash code: " + hashCode);
					System.out.println(docMetaInfoList[i].getDocumentMeta());

					// Download file
					String fileSavePath = fileDownloadFolder + documentId + "_"
							+ docMetaInfoList[i].getFileName();
					documentAPI.downloadDocument(docMetaInfoList[i],
							fileSavePath);

					System.out.println("File Saved to: " + fileSavePath);
				} catch (Exception e) {
					System.out.println(e.toString());
				}
				System.out.println("============");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void downloadDocument(int documentId, String fileDownloadFolder) {
		try {
			// Get document meta information
			DocumentMetaInfo docMetaInfo = documentAPI
					.getDocumentMetaInfo(documentId);
			int fileSize = docMetaInfo.getFileSize();
			String hashCode = docMetaInfo.getFileSHA1HashCode();
			// Print meta info to console
			System.out.println("DocumentId: " + documentId);
			System.out.println("File Size: " + fileSize);
			System.out.println("SHA1 hash code: " + hashCode);
			System.out.println(docMetaInfo.getDocumentMeta());

			// Download file
			System.out.println("Downloading....");
			String fileSavePath = fileDownloadFolder + documentId + "_"
					+ docMetaInfo.getFileName();
			documentAPI.downloadDocument(docMetaInfo, fileSavePath);
			System.out.println("File Saved to: " + fileSavePath);

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public DocumentMetaInfo uploadDocument(String filePath) {
		DocumentMetaInfo docMetaInfo = null;
		try {
			File file = new File(filePath);
			String fileName = file.getName();

			// Add time zone to your date time String.
			// Format: "yyyy-MM-ddTHH:mm:ss+00:00"
			// Sample (Beijing Time): "2009-11-12T11:23:00+08:00"
			String fileCreationDate = "2009-11-12T11:23:00+08:00";// .ToUniversalTime().ToString("yyyy-MM-ddTHH:mm:ss+00:00");
			String fileModifiedDate = "2009-11-12T11:23:00+08:00";// String.valueOf(file.lastModified());//.ToUniversalTime().ToString("yyyy-MM-ddTHH:mm:ss+00:00");

			// Char(3) file format. in upper case.
			String fileFormat = fileName.substring(fileName.lastIndexOf('.'))
					.substring(1, 4).toUpperCase();

			// Check documentation on DocumentWarehouse website to get your
			// document group number.
			int documentGroup = 99;

			// Each document group has its standard META xsd schema,
			// please make sure this XML String is following the schema of
			// corresponding document group.
			// For more information, please go to:
			// http://datamanager.morningstar.com/DocumentWarehouse/DocumentGroups.aspx
			String documentMeta = "<DocumentMeta xmlns=\"\"><ProviderName>ABCDEFG Bank</ProviderName><FileSourceURL>http://DEV/testfile.xls</FileSourceURL><ApprovedBy>Figgo Li</ApprovedBy></DocumentMeta>";

			// Check "Attribute Master List" documentation on DocumentWarehouse
			// to get the attribute IDs you need to tag this document
			// For more details, check link
			// http://datamanager.morningstar.com/DocumentWarehouse/DocumentAttributeList.aspx
			// Sample:
			// 4 - This is a document contains Marketing and Sales Data
			// 19 - this is a Annual Report
			// 62 - this document's form type is 10-K/A
			// 451 - language: English
			int[] attributeIds = new int[] { 4, 19, 62, 451 };

			docMetaInfo = documentAPI.uploadNewDocument(fileName, fileFormat,
					documentGroup, fileCreationDate, fileModifiedDate,
					documentMeta, attributeIds, file);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return docMetaInfo;
	}

	public DocumentMetaInfo updateDocument(int documentId, String filePath) {
		DocumentMetaInfo docMetaInfo = null;
		try {
			File file = new File(filePath);
			String fileName = file.getName();

			// Add time zone to your date time String.
			// Format: "yyyy-MM-ddTHH:mm:ss+00:00"
			// Sample (Beijing Time): "2009-11-12T11:23:00+08:00"
			String fileCreationDate = "2009-11-12T11:23:00+08:00";// .ToUniversalTime().ToString("yyyy-MM-ddTHH:mm:ss+00:00");
			String fileModifiedDate = "2009-11-12T11:23:00+08:00";// String.valueOf(file.lastModified());//.ToUniversalTime().ToString("yyyy-MM-ddTHH:mm:ss+00:00");

			// Char(3) file format. in upper case.
			String fileFormat = fileName.substring(fileName.lastIndexOf('.'))
					.substring(1, 4).toUpperCase();

			// Check documentation on DocumentWarehouse website to get your
			// document group number.
			int documentGroup = 99;

			// Each document group has its standard META xsd schema,
			// please make sure this XML String is following the schema of
			// corresponding document group.
			// For more information, please go to:
			// http://datamanager.morningstar.com/DocumentWarehouse/DocumentGroups.aspx
			String documentMeta = "<DocumentMeta xmlns=\"\"><ProviderName>ABCDEFG Bank</ProviderName><FileSourceURL>http://DEV/testfile.xls</FileSourceURL><ApprovedBy>Figgo Li</ApprovedBy></DocumentMeta>";

			// Check "Attribute Master List" documentation on DocumentWarehouse
			// to get the attribute IDs you need to tag this document
			// For more details, check link
			// http://datamanager.morningstar.com/DocumentWarehouse/DocumentAttributeList.aspx
			// Sample:
			// 4 - This is a document contains Marketing and Sales Data
			// 19 - this is a Annual Report
			// 62 - this document's form type is 10-K/A
			// 451 - language: English
			int[] attributeIds = new int[] { 4, 19, 62, 451 };

			docMetaInfo = documentAPI.updateDocument(documentId, fileName,
					fileFormat, documentGroup, fileCreationDate,
					fileModifiedDate, documentMeta, attributeIds, file);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return docMetaInfo;
	}

	public static void main(String[] args) {
		try {
			String fileDownloadFolder = "C:\\temp\\";
			
			SampleCode sample = new SampleCode();

			// Sample 1 ---- Download files from a document group.
//			sample.downloadDocuments(fileDownloadFolder);

			// Sample 2 ---- Download a document by documentId.
			sample.downloadDocument(8732072, fileDownloadFolder);

			// Sample 3 --- Upload a new file		
			String filePathToUpload = "C:\\temp\\12109801.pdf";
			System.out.println("Uploading...");
			DocumentMetaInfo docMetaInfo = sample.uploadDocument(filePathToUpload);
			if(docMetaInfo!=null){
				System.out.println(docMetaInfo.getFileName() + "(documentId:" + docMetaInfo.getDocumentId() + ") uploaded successfully.");
			}
			
/*			// sample 4 ---- Update a document in document warehouse.
     		String filePathToUpdate = "D:\\YourFolder\\YourTestFile.pdf";			
			int documentId = 5583868;
			DocumentMetaInfo docMetaInfo1 = sample.updateDocument(documentId, filePathToUpdate);
			System.out.println("Document (documentId:" + docMetaInfo1.getDocumentId() + ") updated successfully.");*/
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
