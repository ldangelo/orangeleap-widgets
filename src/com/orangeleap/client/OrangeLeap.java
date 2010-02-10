package com.orangeleap.client;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.1.9
 * Thu Feb 04 16:33:33 CST 2010
 * Generated source version: 2.1.9
 * 
 */
 
@WebService(targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/", name = "OrangeLeap")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface OrangeLeap {

    @WebResult(name = "GetConstituentPledgeResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/", partName = "GetConstituentPledgeResponse")
    @WebMethod(operationName = "GetConstituentPledge")
    public GetConstituentPledgeResponse getConstituentPledge(
        @WebParam(partName = "GetConstituentPledgeRequest", name = "GetConstituentPledgeRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/")
        GetConstituentPledgeRequest getConstituentPledgeRequest
    );

    @WebResult(name = "GetPickListsResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/", partName = "GetPickListsResponse")
    @WebMethod(operationName = "GetPickLists")
    public GetPickListsResponse getPickLists(
        @WebParam(partName = "GetPickListsRequest", name = "GetPickListsRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/")
        GetPickListsRequest getPickListsRequest
    );

    @WebResult(name = "SaveOrUpdateGiftResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/", partName = "SaveOrUpdateGiftResponse")
    @WebMethod(operationName = "SaveOrUpdateGift")
    public SaveOrUpdateGiftResponse saveOrUpdateGift(
        @WebParam(partName = "SaveOrUpdateGiftRequest", name = "SaveOrUpdateGiftRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/")
        SaveOrUpdateGiftRequest saveOrUpdateGiftRequest
    );

    @WebResult(name = "GetSegmentationListResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/", partName = "GetSegmentationListResponse")
    @WebMethod(operationName = "GetSegmentationList")
    public GetSegmentationListResponse getSegmentationList(
        @WebParam(partName = "GetSegmentationListRequest", name = "GetSegmentationListRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/")
        GetSegmentationListRequest getSegmentationListRequest
    );

    @WebResult(name = "GetSegmentationByIdResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/", partName = "GetSegmentationByIdResponse")
    @WebMethod(operationName = "GetSegmentationById")
    public GetSegmentationByIdResponse getSegmentationById(
        @WebParam(partName = "GetSegmentationByIdRequest", name = "GetSegmentationByIdRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/")
        GetSegmentationByIdRequest getSegmentationByIdRequest
    );

    @WebResult(name = "GetPickListByNameResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/", partName = "GetPickListByNameResponse")
    @WebMethod(operationName = "GetPickListByName")
    public GetPickListByNameResponse getPickListByName(
        @WebParam(partName = "GetPickListByNameRequest", name = "GetPickListByNameRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/")
        GetPickListByNameRequest getPickListByNameRequest
    );

    @WebResult(name = "SearchConstituentsResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/", partName = "SearchConstituentsResponse")
    @WebMethod(operationName = "SearchConstituents")
    public SearchConstituentsResponse searchConstituents(
        @WebParam(partName = "SearchConstituentsRequest", name = "SearchConstituentsRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/")
        SearchConstituentsRequest searchConstituentsRequest
    );

    @WebResult(name = "CreateDefaultConstituentResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/", partName = "CreateDefaultConstituentResponse")
    @WebMethod(operationName = "CreateDefaultConstituent")
    public CreateDefaultConstituentResponse createDefaultConstituent(
        @WebParam(partName = "CreateDefaultConstituentRequest", name = "CreateDefaultConstituentRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/")
        CreateDefaultConstituentRequest createDefaultConstituentRequest
    );

    @WebResult(name = "GetConstituentRecurringGiftResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/", partName = "GetConstituentRecurringGiftResponse")
    @WebMethod(operationName = "GetConstituentRecurringGift")
    public GetConstituentRecurringGiftResponse getConstituentRecurringGift(
        @WebParam(partName = "GetConstituentRecurringGiftRequest", name = "GetConstituentRecurringGiftRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/")
        GetConstituentRecurringGiftRequest getConstituentRecurringGiftRequest
    );

    @WebResult(name = "GetConstituentByIdResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/", partName = "GetConstituentByIdResponse")
    @WebMethod(operationName = "GetConstituentById")
    public GetConstituentByIdResponse getConstituentById(
        @WebParam(partName = "GetConstituentByIdRequest", name = "GetConstituentByIdRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/")
        GetConstituentByIdRequest getConstituentByIdRequest
    );

    @WebResult(name = "SaveOrUpdateConstituentResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/", partName = "SaveOrUpdateConstituentResponse")
    @WebMethod(operationName = "SaveOrUpdateConstituent")
    public SaveOrUpdateConstituentResponse saveOrUpdateConstituent(
        @WebParam(partName = "SaveOrUpdateConstituentRequest", name = "SaveOrUpdateConstituentRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/")
        SaveOrUpdateConstituentRequest saveOrUpdateConstituentRequest
    );

    @WebResult(name = "GetConstituentGiftResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/", partName = "GetConstituentGiftResponse")
    @WebMethod(operationName = "GetConstituentGift")
    public GetConstituentGiftResponse getConstituentGift(
        @WebParam(partName = "GetConstituentGiftRequest", name = "GetConstituentGiftRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/")
        GetConstituentGiftRequest getConstituentGiftRequest
    );

    @WebResult(name = "FindConstituentsResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/", partName = "FindConstituentsResponse")
    @WebMethod(operationName = "FindConstituents")
    public FindConstituentsResponse findConstituents(
        @WebParam(partName = "FindConstituentsRequest", name = "FindConstituentsRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/")
        FindConstituentsRequest findConstituentsRequest
    );

    @Oneway
    @WebMethod(operationName = "AddCommunicationHistory")
    public void addCommunicationHistory(
        @WebParam(partName = "AddCommunicationHistoryRequest", name = "AddCommunicationHistoryRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/")
        AddCommunicationHistoryRequest addCommunicationHistoryRequest
    );

    @WebResult(name = "GetCommunicationHistoryResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/", partName = "GetCommunicationHistoryResponse")
    @WebMethod(operationName = "GetCommunicationHistory")
    public GetCommunicationHistoryResponse getCommunicationHistory(
        @WebParam(partName = "GetCommunicationHistoryRequest", name = "GetCommunicationHistoryRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/")
        GetCommunicationHistoryRequest getCommunicationHistoryRequest
    );

    @WebResult(name = "GetSegmentationListByTypeResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/", partName = "GetSegmentationListByTypeResponse")
    @WebMethod(operationName = "GetSegmentationListByType")
    public GetSegmentationListByTypeResponse getSegmentationListByType(
        @WebParam(partName = "GetSegmentationListByTypeRequest", name = "GetSegmentationListByTypeRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/")
        GetSegmentationListByTypeRequest getSegmentationListByTypeRequest
    );

    @WebResult(name = "SaveOrUpdateRecurringGiftResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/", partName = "SaveOrUpdateRecurringGiftResponse")
    @WebMethod(operationName = "SaveOrUpdateRecurringGift")
    public SaveOrUpdateRecurringGiftResponse saveOrUpdateRecurringGift(
        @WebParam(partName = "SaveOrUpdateRecurringGiftRequest", name = "SaveOrUpdateRecurringGiftRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/")
        SaveOrUpdateRecurringGiftRequest saveOrUpdateRecurringGiftRequest
    );

    @Oneway
    @WebMethod(operationName = "BulkAddCommunicationHistory")
    public void bulkAddCommunicationHistory(
        @WebParam(partName = "BulkAddCommunicationHistoryRequest", name = "BulkAddCommunicationHistoryRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/")
        BulkAddCommunicationHistoryRequest bulkAddCommunicationHistoryRequest
    );

    @WebResult(name = "GetSegmentationByNameResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/", partName = "GetSegmentationByNameResponse")
    @WebMethod(operationName = "GetSegmentationByName")
    public GetSegmentationByNameResponse getSegmentationByName(
        @WebParam(partName = "GetSegmentationByNameRequest", name = "GetSegmentationByNameRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/")
        GetSegmentationByNameRequest getSegmentationByNameRequest
    );

    @WebResult(name = "SaveOrUpdatePledgeResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/", partName = "SaveOrUpdatePledgeResponse")
    @WebMethod(operationName = "SaveOrUpdatePledge")
    public SaveOrUpdatePledgeResponse saveOrUpdatePledge(
        @WebParam(partName = "SaveOrUpdatePledgeRequest", name = "SaveOrUpdatePledgeRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services2.0/")
        SaveOrUpdatePledgeRequest saveOrUpdatePledgeRequest
    );
}
