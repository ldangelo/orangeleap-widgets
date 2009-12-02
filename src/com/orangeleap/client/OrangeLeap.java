package com.orangeleap.client;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.2.3
 * Mon Nov 30 10:52:41 CST 2009
 * Generated source version: 2.2.3
 * 
 */
 
@WebService(targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0", name = "OrangeLeap")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface OrangeLeap {

    @WebResult(name = "SaveOrUpdatePledgeResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0", partName = "SaveOrUpdatePledgeResponse")
    @WebMethod(operationName = "SaveOrUpdatePledge")
    public SaveOrUpdatePledgeResponse saveOrUpdatePledge(
        @WebParam(partName = "SaveOrUpdatePledgeRequest", name = "SaveOrUpdatePledgeRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0")
        SaveOrUpdatePledgeRequest saveOrUpdatePledgeRequest
    );

    @WebResult(name = "GetConstituentByIdResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0", partName = "GetConstituentByIdResponse")
    @WebMethod(operationName = "GetConstituentById")
    public GetConstituentByIdResponse getConstituentById(
        @WebParam(partName = "GetConstituentByIdRequest", name = "GetConstituentByIdRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0")
        GetConstituentByIdRequest getConstituentByIdRequest
    );

    @WebResult(name = "SaveOrUpdateGiftResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0", partName = "SaveOrUpdateGiftResponse")
    @WebMethod(operationName = "SaveOrUpdateGift")
    public SaveOrUpdateGiftResponse saveOrUpdateGift(
        @WebParam(partName = "SaveOrUpdateGiftRequest", name = "SaveOrUpdateGiftRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0")
        SaveOrUpdateGiftRequest saveOrUpdateGiftRequest
    );

    @WebResult(name = "GetSegmentationListByTypeResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0", partName = "GetSegmentationListByTypeResponse")
    @WebMethod(operationName = "GetSegmentationListByType")
    public GetSegmentationListByTypeResponse getSegmentationListByType(
        @WebParam(partName = "GetSegmentationListByTypeRequest", name = "GetSegmentationListByTypeRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0")
        GetSegmentationListByTypeRequest getSegmentationListByTypeRequest
    );

    @WebResult(name = "GetSegmentationByIdResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0", partName = "GetSegmentationByIdResponse")
    @WebMethod(operationName = "GetSegmentationById")
    public GetSegmentationByIdResponse getSegmentationById(
        @WebParam(partName = "GetSegmentationByIdRequest", name = "GetSegmentationByIdRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0")
        GetSegmentationByIdRequest getSegmentationByIdRequest
    );

    @Oneway
    @WebMethod(operationName = "AddCommunicationHistory")
    public void addCommunicationHistory(
        @WebParam(partName = "AddCommunicationHistoryRequest", name = "AddCommunicationHistoryRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0")
        AddCommunicationHistoryRequest addCommunicationHistoryRequest
    );

    @WebResult(name = "SaveOrUpdateConstituentResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0", partName = "SaveOrUpdateConstituentResponse")
    @WebMethod(operationName = "SaveOrUpdateConstituent")
    public SaveOrUpdateConstituentResponse saveOrUpdateConstituent(
        @WebParam(partName = "SaveOrUpdateConstituentRequest", name = "SaveOrUpdateConstituentRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0")
        SaveOrUpdateConstituentRequest saveOrUpdateConstituentRequest
    );

    @WebResult(name = "FindConstituentsResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0", partName = "FindConstituentsResponse")
    @WebMethod(operationName = "FindConstituents")
    public FindConstituentsResponse findConstituents(
        @WebParam(partName = "FindConstituentsRequest", name = "FindConstituentsRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0")
        FindConstituentsRequest findConstituentsRequest
    );

    @WebResult(name = "GetConstituentGiftResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0", partName = "GetConstituentGiftResponse")
    @WebMethod(operationName = "GetConstituentGift")
    public GetConstituentGiftResponse getConstituentGift(
        @WebParam(partName = "GetConstituentGiftRequest", name = "GetConstituentGiftRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0")
        GetConstituentGiftRequest getConstituentGiftRequest
    );

    @WebResult(name = "SearchConstituentsResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0", partName = "SearchConstituentsResponse")
    @WebMethod(operationName = "SearchConstituents")
    public SearchConstituentsResponse searchConstituents(
        @WebParam(partName = "SearchConstituentsRequest", name = "SearchConstituentsRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0")
        SearchConstituentsRequest searchConstituentsRequest
    );

    @WebResult(name = "GetCommunicationHistoryResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0", partName = "GetCommunicationHistoryResponse")
    @WebMethod(operationName = "GetCommunicationHistory")
    public GetCommunicationHistoryResponse getCommunicationHistory(
        @WebParam(partName = "GetCommunicationHistoryRequest", name = "GetCommunicationHistoryRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0")
        GetCommunicationHistoryRequest getCommunicationHistoryRequest
    );

    @WebResult(name = "GetSegmentationListResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0", partName = "GetSegmentationListResponse")
    @WebMethod(operationName = "GetSegmentationList")
    public GetSegmentationListResponse getSegmentationList(
        @WebParam(partName = "GetSegmentationListRequest", name = "GetSegmentationListRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0")
        GetSegmentationListRequest getSegmentationListRequest
    );

    @WebResult(name = "CreateDefaultConstituentResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0", partName = "CreateDefaultConstituentResponse")
    @WebMethod(operationName = "CreateDefaultConstituent")
    public CreateDefaultConstituentResponse createDefaultConstituent(
        @WebParam(partName = "CreateDefaultConstituentRequest", name = "CreateDefaultConstituentRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0")
        CreateDefaultConstituentRequest createDefaultConstituentRequest
    );

    @Oneway
    @WebMethod(operationName = "BulkAddCommunicationHistory")
    public void bulkAddCommunicationHistory(
        @WebParam(partName = "BulkAddCommunicationHistoryRequest", name = "BulkAddCommunicationHistoryRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0")
        BulkAddCommunicationHistoryRequest bulkAddCommunicationHistoryRequest
    );

    @WebResult(name = "GetSegmentationByNameResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0", partName = "GetSegmentationByNameResponse")
    @WebMethod(operationName = "GetSegmentationByName")
    public GetSegmentationByNameResponse getSegmentationByName(
        @WebParam(partName = "GetSegmentationByNameRequest", name = "GetSegmentationByNameRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0")
        GetSegmentationByNameRequest getSegmentationByNameRequest
    );

    @WebResult(name = "GetConstituentPledgeResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0", partName = "GetConstituentPledgeResponse")
    @WebMethod(operationName = "GetConstituentPledge")
    public GetConstituentPledgeResponse getConstituentPledge(
        @WebParam(partName = "GetConstituentPledgeRequest", name = "GetConstituentPledgeRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services/1.0")
        GetConstituentPledgeRequest getConstituentPledgeRequest
    );
}
