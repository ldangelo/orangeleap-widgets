package com.orangeleap.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.2.4
 * Wed Apr 17 17:11:48 CDT 2013
 * Generated source version: 2.2.4
 * 
 */
 
@WebService(targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", name = "OrangeLeap")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface OrangeLeap {

    @WebResult(name = "ListEntitiesResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "ListEntitiesResponse")
    @WebMethod(operationName = "ListEntities")
    public ListEntitiesResponse listEntities(
        @WebParam(partName = "ListEntitiesRequest", name = "ListEntitiesRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        ListEntitiesRequest listEntitiesRequest
    );

    @WebResult(name = "GetSegmentationListResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetSegmentationListResponse")
    @WebMethod(operationName = "GetSegmentationList")
    public GetSegmentationListResponse getSegmentationList(
        @WebParam(partName = "GetSegmentationListRequest", name = "GetSegmentationListRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetSegmentationListRequest getSegmentationListRequest
    );

    @WebResult(name = "GetPostalCodesByRadiusResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetPostalCodesByRadiusResponse")
    @WebMethod(operationName = "GetPostalCodesByRadius")
    public GetPostalCodesByRadiusResponse getPostalCodesByRadius(
        @WebParam(partName = "GetPostalCodesByRadiusRequest", name = "GetPostalCodesByRadiusRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetPostalCodesByRadiusRequest getPostalCodesByRadiusRequest
    );

    @WebResult(name = "SaveOrUpdatePledgeResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "SaveOrUpdatePledgeResponse")
    @WebMethod(operationName = "SaveOrUpdatePledge")
    public SaveOrUpdatePledgeResponse saveOrUpdatePledge(
        @WebParam(partName = "SaveOrUpdatePledgeRequest", name = "SaveOrUpdatePledgeRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        SaveOrUpdatePledgeRequest saveOrUpdatePledgeRequest
    );

    @WebResult(name = "GetConstituentGiftResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetConstituentGiftResponse")
    @WebMethod(operationName = "GetConstituentGift")
    public GetConstituentGiftResponse getConstituentGift(
        @WebParam(partName = "GetConstituentGiftRequest", name = "GetConstituentGiftRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetConstituentGiftRequest getConstituentGiftRequest
    );

    @WebResult(name = "SaveOrUpdateCommunicationHistoryResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "SaveOrUpdateCommunicationHistoryResponse")
    @WebMethod(operationName = "SaveOrUpdateCommunicationHistory")
    public SaveOrUpdateCommunicationHistoryResponse saveOrUpdateCommunicationHistory(
        @WebParam(partName = "SaveOrUpdateCommunicationHistoryRequest", name = "SaveOrUpdateCommunicationHistoryRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        SaveOrUpdateCommunicationHistoryRequest saveOrUpdateCommunicationHistoryRequest
    );

    @WebResult(name = "GetPickListsCountResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetPickListsCountResponse")
    @WebMethod(operationName = "GetPickListsCount")
    public GetPickListsCountResponse getPickListsCount(
        @WebParam(partName = "GetPickListsCountRequest", name = "GetPickListsCountRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetPickListsCountRequest getPickListsCountRequest
    );

    @WebResult(name = "ImportResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "ImportResponse")
    @WebMethod(operationName = "Import")
    public ImportResponse _import(
        @WebParam(partName = "ImportRequest", name = "ImportRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        ImportRequest importRequest
    );

    @WebResult(name = "GetPaymentSourcesByConstituentIdResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetPaymentSourcesByConstituentIdResponse")
    @WebMethod(operationName = "GetPaymentSourcesByConstituentId")
    public GetPaymentSourcesByConstituentIdResponse getPaymentSourcesByConstituentId(
        @WebParam(partName = "GetPaymentSourcesByConstituentIdRequest", name = "GetPaymentSourcesByConstituentIdRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetPaymentSourcesByConstituentIdRequest getPaymentSourcesByConstituentIdRequest
    );

    @WebResult(name = "GetListMasterCustomTablesResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetListMasterCustomTablesResponse")
    @WebMethod(operationName = "GetListMasterCustomTables")
    public GetListMasterCustomTablesResponse getListMasterCustomTables(
        @WebParam(partName = "GetListMasterCustomTablesRequest", name = "GetListMasterCustomTablesRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetListMasterCustomTablesRequest getListMasterCustomTablesRequest
    );

    @WebResult(name = "GetConstituentByListIdResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetConstituentByListIdResponse")
    @WebMethod(operationName = "GetConstituentByListId")
    public GetConstituentByListIdResponse getConstituentByListId(
        @WebParam(partName = "GetConstituentByListIdRequest", name = "GetConstituentByListIdRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetConstituentByListIdRequest getConstituentByListIdRequest
    );

    @WebResult(name = "GetCustomTableRowCountResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetCustomTableRowCountResponse")
    @WebMethod(operationName = "GetCustomTableRowCount")
    public GetCustomTableRowCountResponse getCustomTableRowCount(
        @WebParam(partName = "GetCustomTableRowCountRequest", name = "GetCustomTableRowCountRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetCustomTableRowCountRequest getCustomTableRowCountRequest
    );

    @WebResult(name = "BulkAddCommunicationHistoryResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "BulkAddCommunicationHistoryResponse")
    @WebMethod(operationName = "BulkAddCommunicationHistory")
    public BulkAddCommunicationHistoryResponse bulkAddCommunicationHistory(
        @WebParam(partName = "BulkAddCommunicationHistoryRequest", name = "BulkAddCommunicationHistoryRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        BulkAddCommunicationHistoryRequest bulkAddCommunicationHistoryRequest
    );

    @WebResult(name = "ListCustomTableFieldsResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "ListCustomTableFieldsResponse")
    @WebMethod(operationName = "ListCustomTableFields")
    public ListCustomTableFieldsResponse listCustomTableFields(
        @WebParam(partName = "ListCustomTableFieldsRequest", name = "ListCustomTableFieldsRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        ListCustomTableFieldsRequest listCustomTableFieldsRequest
    );

    @WebResult(name = "ReadCustomTableFieldByIdResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "ReadCustomTableFieldByIdResponse")
    @WebMethod(operationName = "ReadCustomTableFieldById")
    public ReadCustomTableFieldByIdResponse readCustomTableFieldById(
        @WebParam(partName = "ReadCustomTableFieldByIdRequest", name = "ReadCustomTableFieldByIdRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        ReadCustomTableFieldByIdRequest readCustomTableFieldByIdRequest
    );

    @WebResult(name = "GetConstituentPledgeCountResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetConstituentPledgeCountResponse")
    @WebMethod(operationName = "GetConstituentPledgeCount")
    public GetConstituentPledgeCountResponse getConstituentPledgeCount(
        @WebParam(partName = "GetConstituentPledgeCountRequest", name = "GetConstituentPledgeCountRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetConstituentPledgeCountRequest getConstituentPledgeCountRequest
    );

    @WebResult(name = "SaveOrUpdateCustomTableRowResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "SaveOrUpdateCustomTableRowResponse")
    @WebMethod(operationName = "SaveOrUpdateCustomTableRow")
    public SaveOrUpdateCustomTableRowResponse saveOrUpdateCustomTableRow(
        @WebParam(partName = "SaveOrUpdateCustomTableRowRequest", name = "SaveOrUpdateCustomTableRowRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        SaveOrUpdateCustomTableRowRequest saveOrUpdateCustomTableRowRequest
    );

    @WebResult(name = "GetCustomTableTitleFieldResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetCustomTableTitleFieldResponse")
    @WebMethod(operationName = "GetCustomTableTitleField")
    public GetCustomTableTitleFieldResponse getCustomTableTitleField(
        @WebParam(partName = "GetCustomTableTitleFieldRequest", name = "GetCustomTableTitleFieldRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetCustomTableTitleFieldRequest getCustomTableTitleFieldRequest
    );

    @WebResult(name = "GetCustomTableConstituentContextFieldResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetCustomTableConstituentContextFieldResponse")
    @WebMethod(operationName = "GetCustomTableConstituentContextField")
    public GetCustomTableConstituentContextFieldResponse getCustomTableConstituentContextField(
        @WebParam(partName = "GetCustomTableConstituentContextFieldRequest", name = "GetCustomTableConstituentContextFieldRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetCustomTableConstituentContextFieldRequest getCustomTableConstituentContextFieldRequest
    );

    @WebResult(name = "SaveOrUpdateGiftResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "SaveOrUpdateGiftResponse")
    @WebMethod(operationName = "SaveOrUpdateGift")
    public SaveOrUpdateGiftResponse saveOrUpdateGift(
        @WebParam(partName = "SaveOrUpdateGiftRequest", name = "SaveOrUpdateGiftRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        SaveOrUpdateGiftRequest saveOrUpdateGiftRequest
    );

    @WebResult(name = "SetEntityResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "SetEntityResponse")
    @WebMethod(operationName = "SetEntity")
    public SetEntityResponse setEntity(
        @WebParam(partName = "SetEntityRequest", name = "SetEntityRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        SetEntityRequest setEntityRequest
    );

    @WebResult(name = "GetPickListByNameResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetPickListByNameResponse")
    @WebMethod(operationName = "GetPickListByName")
    public GetPickListByNameResponse getPickListByName(
        @WebParam(partName = "GetPickListByNameRequest", name = "GetPickListByNameRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetPickListByNameRequest getPickListByNameRequest
    );

    @WebResult(name = "GetJobStatusResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetJobStatusResponse")
    @WebMethod(operationName = "GetJobStatus")
    public GetJobStatusResponse getJobStatus(
        @WebParam(partName = "GetJobStatusRequest", name = "GetJobStatusRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetJobStatusRequest getJobStatusRequest
    );

    @WebResult(name = "GetCommunicationHistoryCountResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetCommunicationHistoryCountResponse")
    @WebMethod(operationName = "GetCommunicationHistoryCount")
    public GetCommunicationHistoryCountResponse getCommunicationHistoryCount(
        @WebParam(partName = "GetCommunicationHistoryCountRequest", name = "GetCommunicationHistoryCountRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetCommunicationHistoryCountRequest getCommunicationHistoryCountRequest
    );

    @WebResult(name = "GetConstituentByPaymentSourceResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetConstituentByPaymentSourceResponse")
    @WebMethod(operationName = "GetConstituentByPaymentSource")
    public GetConstituentByPaymentSourceResponse getConstituentByPaymentSource(
        @WebParam(partName = "GetConstituentByPaymentSourceRequest", name = "GetConstituentByPaymentSourceRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetConstituentByPaymentSourceRequest getConstituentByPaymentSourceRequest
    );

    @WebResult(name = "ReadCustomTableByNameResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "ReadCustomTableByNameResponse")
    @WebMethod(operationName = "ReadCustomTableByName")
    public ReadCustomTableByNameResponse readCustomTableByName(
        @WebParam(partName = "ReadCustomTableByNameRequest", name = "ReadCustomTableByNameRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        ReadCustomTableByNameRequest readCustomTableByNameRequest
    );

    @WebResult(name = "GetPickListsResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetPickListsResponse")
    @WebMethod(operationName = "GetPickLists")
    public GetPickListsResponse getPickLists(
        @WebParam(partName = "GetPickListsRequest", name = "GetPickListsRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetPickListsRequest getPickListsRequest
    );

    @WebResult(name = "GetSegmentationListByTypeResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetSegmentationListByTypeResponse")
    @WebMethod(operationName = "GetSegmentationListByType")
    public GetSegmentationListByTypeResponse getSegmentationListByType(
        @WebParam(partName = "GetSegmentationListByTypeRequest", name = "GetSegmentationListByTypeRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetSegmentationListByTypeRequest getSegmentationListByTypeRequest
    );

    @WebResult(name = "GetJobResultResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetJobResultResponse")
    @WebMethod(operationName = "GetJobResult")
    public GetJobResultResponse getJobResult(
        @WebParam(partName = "GetJobResultRequest", name = "GetJobResultRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetJobResultRequest getJobResultRequest
    );

    @WebResult(name = "GetSegmentationListCountResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetSegmentationListCountResponse")
    @WebMethod(operationName = "GetSegmentationListCount")
    public GetSegmentationListCountResponse getSegmentationListCount(
        @WebParam(partName = "GetSegmentationListCountRequest", name = "GetSegmentationListCountRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetSegmentationListCountRequest getSegmentationListCountRequest
    );

    @WebResult(name = "CreateDefaultConstituentResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "CreateDefaultConstituentResponse")
    @WebMethod(operationName = "CreateDefaultConstituent")
    public CreateDefaultConstituentResponse createDefaultConstituent(
        @WebParam(partName = "CreateDefaultConstituentRequest", name = "CreateDefaultConstituentRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        CreateDefaultConstituentRequest createDefaultConstituentRequest
    );

    @WebResult(name = "SearchConstituentsResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "SearchConstituentsResponse")
    @WebMethod(operationName = "SearchConstituents")
    public SearchConstituentsResponse searchConstituents(
        @WebParam(partName = "SearchConstituentsRequest", name = "SearchConstituentsRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        SearchConstituentsRequest searchConstituentsRequest
    );

    @WebResult(name = "GetConstituentByIdResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetConstituentByIdResponse")
    @WebMethod(operationName = "GetConstituentById")
    public GetConstituentByIdResponse getConstituentById(
        @WebParam(partName = "GetConstituentByIdRequest", name = "GetConstituentByIdRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetConstituentByIdRequest getConstituentByIdRequest
    );

    @WebResult(name = "GetConstituentRecurringGiftCountResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetConstituentRecurringGiftCountResponse")
    @WebMethod(operationName = "GetConstituentRecurringGiftCount")
    public GetConstituentRecurringGiftCountResponse getConstituentRecurringGiftCount(
        @WebParam(partName = "GetConstituentRecurringGiftCountRequest", name = "GetConstituentRecurringGiftCountRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetConstituentRecurringGiftCountRequest getConstituentRecurringGiftCountRequest
    );

    @WebResult(name = "FindConstituentsResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "FindConstituentsResponse")
    @WebMethod(operationName = "FindConstituents")
    public FindConstituentsResponse findConstituents(
        @WebParam(partName = "FindConstituentsRequest", name = "FindConstituentsRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        FindConstituentsRequest findConstituentsRequest
    );

    @WebResult(name = "GetSegmentationByNameResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetSegmentationByNameResponse")
    @WebMethod(operationName = "GetSegmentationByName")
    public GetSegmentationByNameResponse getSegmentationByName(
        @WebParam(partName = "GetSegmentationByNameRequest", name = "GetSegmentationByNameRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetSegmentationByNameRequest getSegmentationByNameRequest
    );

    @WebResult(name = "GetConstituentGiftCountResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetConstituentGiftCountResponse")
    @WebMethod(operationName = "GetConstituentGiftCount")
    public GetConstituentGiftCountResponse getConstituentGiftCount(
        @WebParam(partName = "GetConstituentGiftCountRequest", name = "GetConstituentGiftCountRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetConstituentGiftCountRequest getConstituentGiftCountRequest
    );

    @WebResult(name = "ReadCustomTableByIdResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "ReadCustomTableByIdResponse")
    @WebMethod(operationName = "ReadCustomTableById")
    public ReadCustomTableByIdResponse readCustomTableById(
        @WebParam(partName = "ReadCustomTableByIdRequest", name = "ReadCustomTableByIdRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        ReadCustomTableByIdRequest readCustomTableByIdRequest
    );

    @WebResult(name = "GetCustomTableRowsResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetCustomTableRowsResponse")
    @WebMethod(operationName = "GetCustomTableRows")
    public GetCustomTableRowsResponse getCustomTableRows(
        @WebParam(partName = "GetCustomTableRowsRequest", name = "GetCustomTableRowsRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetCustomTableRowsRequest getCustomTableRowsRequest
    );

    @WebResult(name = "GetConstituentPledgeResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetConstituentPledgeResponse")
    @WebMethod(operationName = "GetConstituentPledge")
    public GetConstituentPledgeResponse getConstituentPledge(
        @WebParam(partName = "GetConstituentPledgeRequest", name = "GetConstituentPledgeRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetConstituentPledgeRequest getConstituentPledgeRequest
    );

    @WebResult(name = "SaveOrUpdateConstituentResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "SaveOrUpdateConstituentResponse")
    @WebMethod(operationName = "SaveOrUpdateConstituent")
    public SaveOrUpdateConstituentResponse saveOrUpdateConstituent(
        @WebParam(partName = "SaveOrUpdateConstituentRequest", name = "SaveOrUpdateConstituentRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        SaveOrUpdateConstituentRequest saveOrUpdateConstituentRequest
    );

    @WebResult(name = "GetListEntityRelatedCustomTablesResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetListEntityRelatedCustomTablesResponse")
    @WebMethod(operationName = "GetListEntityRelatedCustomTables")
    public GetListEntityRelatedCustomTablesResponse getListEntityRelatedCustomTables(
        @WebParam(partName = "GetListEntityRelatedCustomTablesRequest", name = "GetListEntityRelatedCustomTablesRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetListEntityRelatedCustomTablesRequest getListEntityRelatedCustomTablesRequest
    );

    @WebResult(name = "GetSegmentationListByTypeCountResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetSegmentationListByTypeCountResponse")
    @WebMethod(operationName = "GetSegmentationListByTypeCount")
    public GetSegmentationListByTypeCountResponse getSegmentationListByTypeCount(
        @WebParam(partName = "GetSegmentationListByTypeCountRequest", name = "GetSegmentationListByTypeCountRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetSegmentationListByTypeCountRequest getSegmentationListByTypeCountRequest
    );

    @WebResult(name = "GetCommunicationHistoryResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetCommunicationHistoryResponse")
    @WebMethod(operationName = "GetCommunicationHistory")
    public GetCommunicationHistoryResponse getCommunicationHistory(
        @WebParam(partName = "GetCommunicationHistoryRequest", name = "GetCommunicationHistoryRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetCommunicationHistoryRequest getCommunicationHistoryRequest
    );

    @WebResult(name = "ReadCustomTableRowResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "ReadCustomTableRowResponse")
    @WebMethod(operationName = "ReadCustomTableRow")
    public ReadCustomTableRowResponse readCustomTableRow(
        @WebParam(partName = "ReadCustomTableRowRequest", name = "ReadCustomTableRowRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        ReadCustomTableRowRequest readCustomTableRowRequest
    );

    @WebResult(name = "ListCustomTablesResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "ListCustomTablesResponse")
    @WebMethod(operationName = "ListCustomTables")
    public ListCustomTablesResponse listCustomTables(
        @WebParam(partName = "ListCustomTablesRequest", name = "ListCustomTablesRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        ListCustomTablesRequest listCustomTablesRequest
    );

    @WebResult(name = "SaveOrUpdateRecurringGiftResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "SaveOrUpdateRecurringGiftResponse")
    @WebMethod(operationName = "SaveOrUpdateRecurringGift")
    public SaveOrUpdateRecurringGiftResponse saveOrUpdateRecurringGift(
        @WebParam(partName = "SaveOrUpdateRecurringGiftRequest", name = "SaveOrUpdateRecurringGiftRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        SaveOrUpdateRecurringGiftRequest saveOrUpdateRecurringGiftRequest
    );

    @WebResult(name = "AddPickListItemByNameResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "AddPickListItemByNameResponse")
    @WebMethod(operationName = "AddPickListItemByName")
    public AddPickListItemByNameResponse addPickListItemByName(
        @WebParam(partName = "AddPickListItemByNameRequest", name = "AddPickListItemByNameRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        AddPickListItemByNameRequest addPickListItemByNameRequest
    );

    @WebResult(name = "GetSegmentationByIdResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetSegmentationByIdResponse")
    @WebMethod(operationName = "GetSegmentationById")
    public GetSegmentationByIdResponse getSegmentationById(
        @WebParam(partName = "GetSegmentationByIdRequest", name = "GetSegmentationByIdRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetSegmentationByIdRequest getSegmentationByIdRequest
    );

    @WebResult(name = "GetEntityResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetEntityResponse")
    @WebMethod(operationName = "GetEntity")
    public GetEntityResponse getEntity(
        @WebParam(partName = "GetEntityRequest", name = "GetEntityRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetEntityRequest getEntityRequest
    );

    @WebResult(name = "ImportDataResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "ImportDataResponse")
    @WebMethod(operationName = "ImportData")
    public ImportDataResponse importData(
        @WebParam(partName = "ImportDataRequest", name = "ImportDataRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        ImportDataRequest importDataRequest
    );

    @WebResult(name = "GetDetailCustomTableRowsResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetDetailCustomTableRowsResponse")
    @WebMethod(operationName = "GetDetailCustomTableRows")
    public GetDetailCustomTableRowsResponse getDetailCustomTableRows(
        @WebParam(partName = "GetDetailCustomTableRowsRequest", name = "GetDetailCustomTableRowsRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetDetailCustomTableRowsRequest getDetailCustomTableRowsRequest
    );

    @WebResult(name = "GetConstituentRecurringGiftResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetConstituentRecurringGiftResponse")
    @WebMethod(operationName = "GetConstituentRecurringGift")
    public GetConstituentRecurringGiftResponse getConstituentRecurringGift(
        @WebParam(partName = "GetConstituentRecurringGiftRequest", name = "GetConstituentRecurringGiftRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetConstituentRecurringGiftRequest getConstituentRecurringGiftRequest
    );

    @WebResult(name = "GetListDetailCustomTablesResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/", partName = "GetListDetailCustomTablesResponse")
    @WebMethod(operationName = "GetListDetailCustomTables")
    public GetListDetailCustomTablesResponse getListDetailCustomTables(
        @WebParam(partName = "GetListDetailCustomTablesRequest", name = "GetListDetailCustomTablesRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.1/")
        GetListDetailCustomTablesRequest getListDetailCustomTablesRequest
    );
}
