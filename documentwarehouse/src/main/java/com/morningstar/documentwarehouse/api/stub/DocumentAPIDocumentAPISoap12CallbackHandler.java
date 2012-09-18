/**
 * DocumentAPIDocumentAPISoap12CallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */

package com.morningstar.documentwarehouse.api.stub;

/**
 * DocumentAPIDocumentAPISoap12CallbackHandler Callback class, Users can extend
 * this class and implement their own receiveResult and receiveError methods.
 */
public abstract class DocumentAPIDocumentAPISoap12CallbackHandler {

	protected Object clientData;

	/**
	 * User can pass in any object that needs to be accessed once the
	 * NonBlocking Web service call is finished and appropriate method of this
	 * CallBack is called.
	 * 
	 * @param clientData
	 *            Object mechanism by which the user can pass in user data that
	 *            will be avilable at the time this callback is called.
	 */
	public DocumentAPIDocumentAPISoap12CallbackHandler(Object clientData) {
		this.clientData = clientData;
	}

	/**
	 * Please use this constructor if you don't want to set any clientData
	 */
	public DocumentAPIDocumentAPISoap12CallbackHandler() {
		this.clientData = null;
	}

	/**
	 * Get the client data
	 */

	public Object getClientData() {
		return clientData;
	}

	/**
	 * auto generated Axis2 call back method for getDocumentIdList method
	 * override this method for handling normal response from getDocumentIdList
	 * operation
	 */
	public void receiveResultgetDocumentIdList(
			com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.GetDocumentIdListResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getDocumentIdList operation
	 */
	public void receiveErrorgetDocumentIdList(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getDocumentMetaInfo method
	 * override this method for handling normal response from
	 * getDocumentMetaInfo operation
	 */
	public void receiveResultgetDocumentMetaInfo(
			com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.GetDocumentMetaInfoResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getDocumentMetaInfo operation
	 */
	public void receiveErrorgetDocumentMetaInfo(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for deleteDocument method override
	 * this method for handling normal response from deleteDocument operation
	 */
	public void receiveResultdeleteDocument(
			com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.DeleteDocumentResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from deleteDocument operation
	 */
	public void receiveErrordeleteDocument(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getBigDocumentData method
	 * override this method for handling normal response from getBigDocumentData
	 * operation
	 */
	public void receiveResultgetBigDocumentData(
			com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.GetBigDocumentDataResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getBigDocumentData operation
	 */
	public void receiveErrorgetBigDocumentData(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for updateLargeDocumentData method
	 * override this method for handling normal response from
	 * updateLargeDocumentData operation
	 */
	public void receiveResultupdateLargeDocumentData(
			com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.UpdateLargeDocumentDataResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from updateLargeDocumentData operation
	 */
	public void receiveErrorupdateLargeDocumentData(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for updateBigDocumentData method
	 * override this method for handling normal response from
	 * updateBigDocumentData operation
	 */
	public void receiveResultupdateBigDocumentData(
			com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.UpdateBigDocumentDataResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from updateBigDocumentData operation
	 */
	public void receiveErrorupdateBigDocumentData(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getAttributeList method
	 * override this method for handling normal response from getAttributeList
	 * operation
	 */
	public void receiveResultgetAttributeList(
			com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.GetAttributeListResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getAttributeList operation
	 */
	public void receiveErrorgetAttributeList(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for updateDocumentData method
	 * override this method for handling normal response from updateDocumentData
	 * operation
	 */
	public void receiveResultupdateDocumentData(
			com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.UpdateDocumentDataResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from updateDocumentData operation
	 */
	public void receiveErrorupdateDocumentData(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for
	 * getDocumentMetaInfoListByAttributeExpression method override this method
	 * for handling normal response from
	 * getDocumentMetaInfoListByAttributeExpression operation
	 */
	public void receiveResultgetDocumentMetaInfoListByAttributeExpression(
			com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.GetDocumentMetaInfoListByAttributeExpressionResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getDocumentMetaInfoListByAttributeExpression
	 * operation
	 */
	public void receiveErrorgetDocumentMetaInfoListByAttributeExpression(
			java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for restoreDocument method override
	 * this method for handling normal response from restoreDocument operation
	 */
	public void receiveResultrestoreDocument(
			com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.RestoreDocumentResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from restoreDocument operation
	 */
	public void receiveErrorrestoreDocument(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for addDocument method override
	 * this method for handling normal response from addDocument operation
	 */
	public void receiveResultaddDocument(
			com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.AddDocumentResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from addDocument operation
	 */
	public void receiveErroraddDocument(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for updateDocumentMetaInfo method
	 * override this method for handling normal response from
	 * updateDocumentMetaInfo operation
	 */
	public void receiveResultupdateDocumentMetaInfo(
			com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.UpdateDocumentMetaInfoResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from updateDocumentMetaInfo operation
	 */
	public void receiveErrorupdateDocumentMetaInfo(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getDocumentMetaInfoList method
	 * override this method for handling normal response from
	 * getDocumentMetaInfoList operation
	 */
	public void receiveResultgetDocumentMetaInfoList(
			com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.GetDocumentMetaInfoListResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getDocumentMetaInfoList operation
	 */
	public void receiveErrorgetDocumentMetaInfoList(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getDocumentData method override
	 * this method for handling normal response from getDocumentData operation
	 */
	public void receiveResultgetDocumentData(
			com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.GetDocumentDataResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getDocumentData operation
	 */
	public void receiveErrorgetDocumentData(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for updateDocument method override
	 * this method for handling normal response from updateDocument operation
	 */
	public void receiveResultupdateDocument(
			com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.UpdateDocumentResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from updateDocument operation
	 */
	public void receiveErrorupdateDocument(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for addLargeDocument method
	 * override this method for handling normal response from addLargeDocument
	 * operation
	 */
	public void receiveResultaddLargeDocument(
			com.morningstar.documentwarehouse.api.stub.DocumentAPIDocumentAPISoap12Stub.AddLargeDocumentResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from addLargeDocument operation
	 */
	public void receiveErroraddLargeDocument(java.lang.Exception e) {
	}

}
