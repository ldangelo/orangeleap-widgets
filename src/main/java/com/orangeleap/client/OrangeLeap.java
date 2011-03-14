package com.orangeleap.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.1.9
 * Mon Mar 14 16:58:51 CDT 2011
 * Generated source version: 2.1.9
 * 
 */
 
@WebService(targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", name = "OrangeLeap")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface OrangeLeap {

    @WebResult(name = "ListCustomTableFieldsResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "ListCustomTableFieldsResponse")
    @WebMethod(operationName = "ListCustomTableFields")
    public ListCustomTableFieldsResponse listCustomTableFields(
        @WebParam(partName = "ListCustomTableFieldsRequest", name = "ListCustomTableFieldsRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        ListCustomTableFieldsRequest listCustomTableFieldsRequest
    );

    @WebResult(name = "SaveOrUpdateConstituentResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "SaveOrUpdateConstituentResponse")
    @WebMethod(operationName = "SaveOrUpdateConstituent")
    public SaveOrUpdateConstituentResponse saveOrUpdateConstituent(
        @WebParam(partName = "SaveOrUpdateConstituentRequest", name = "SaveOrUpdateConstituentRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        SaveOrUpdateConstituentRequest saveOrUpdateConstituentRequest
    );

    @WebResult(name = "SaveOrUpdateRecurringGiftResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "SaveOrUpdateRecurringGiftResponse")
    @WebMethod(operationName = "SaveOrUpdateRecurringGift")
    public SaveOrUpdateRecurringGiftResponse saveOrUpdateRecurringGift(
        @WebParam(partName = "SaveOrUpdateRecurringGiftRequest", name = "SaveOrUpdateRecurringGiftRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        SaveOrUpdateRecurringGiftRequest saveOrUpdateRecurringGiftRequest
    );

    @WebResult(name = "GetCustomTableRowsResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetCustomTableRowsResponse")
    @WebMethod(operationName = "GetCustomTableRows")
    public GetCustomTableRowsResponse getCustomTableRows(
        @WebParam(partName = "GetCustomTableRowsRequest", name = "GetCustomTableRowsRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetCustomTableRowsRequest getCustomTableRowsRequest
    );

    @WebResult(name = "GetCustomTableRowCountResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetCustomTableRowCountResponse")
    @WebMethod(operationName = "GetCustomTableRowCount")
    public GetCustomTableRowCountResponse getCustomTableRowCount(
        @WebParam(partName = "GetCustomTableRowCountRequest", name = "GetCustomTableRowCountRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetCustomTableRowCountRequest getCustomTableRowCountRequest
    );

    @WebResult(name = "GetCustomTableTitleFieldResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetCustomTableTitleFieldResponse")
    @WebMethod(operationName = "GetCustomTableTitleField")
    public GetCustomTableTitleFieldResponse getCustomTableTitleField(
        @WebParam(partName = "GetCustomTableTitleFieldRequest", name = "GetCustomTableTitleFieldRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetCustomTableTitleFieldRequest getCustomTableTitleFieldRequest
    );

    @WebResult(name = "AddPickListItemByNameResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "AddPickListItemByNameResponse")
    @WebMethod(operationName = "AddPickListItemByName")
    public AddPickListItemByNameResponse addPickListItemByName(
        @WebParam(partName = "AddPickListItemByNameRequest", name = "AddPickListItemByNameRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        AddPickListItemByNameRequest addPickListItemByNameRequest
    );

    @WebResult(name = "GetCustomTableConstituentContextFieldResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetCustomTableConstituentContextFieldResponse")
    @WebMethod(operationName = "GetCustomTableConstituentContextField")
    public GetCustomTableConstituentContextFieldResponse getCustomTableConstituentContextField(
        @WebParam(partName = "GetCustomTableConstituentContextFieldRequest", name = "GetCustomTableConstituentContextFieldRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetCustomTableConstituentContextFieldRequest getCustomTableConstituentContextFieldRequest
    );

    @WebResult(name = "GetConstituentRecurringGiftCountResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetConstituentRecurringGiftCountResponse")
    @WebMethod(operationName = "GetConstituentRecurringGiftCount")
    public GetConstituentRecurringGiftCountResponse getConstituentRecurringGiftCount(
        @WebParam(partName = "GetConstituentRecurringGiftCountRequest", name = "GetConstituentRecurringGiftCountRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetConstituentRecurringGiftCountRequest getConstituentRecurringGiftCountRequest
    );

    @WebResult(name = "GetSegmentationListByTypeResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetSegmentationListByTypeResponse")
    @WebMethod(operationName = "GetSegmentationListByType")
    public GetSegmentationListByTypeResponse getSegmentationListByType(
        @WebParam(partName = "GetSegmentationListByTypeRequest", name = "GetSegmentationListByTypeRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetSegmentationListByTypeRequest getSegmentationListByTypeRequest
    );

    @WebResult(name = "ReadCustomTableRowResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "ReadCustomTableRowResponse")
    @WebMethod(operationName = "ReadCustomTableRow")
    public ReadCustomTableRowResponse readCustomTableRow(
        @WebParam(partName = "ReadCustomTableRowRequest", name = "ReadCustomTableRowRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        ReadCustomTableRowRequest readCustomTableRowRequest
    );

    @WebResult(name = "GetConstituentGiftCountResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetConstituentGiftCountResponse")
    @WebMethod(operationName = "GetConstituentGiftCount")
    public GetConstituentGiftCountResponse getConstituentGiftCount(
        @WebParam(partName = "GetConstituentGiftCountRequest", name = "GetConstituentGiftCountRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetConstituentGiftCountRequest getConstituentGiftCountRequest
    );

    @WebResult(name = "SaveOrUpdatePledgeResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "SaveOrUpdatePledgeResponse")
    @WebMethod(operationName = "SaveOrUpdatePledge")
    public SaveOrUpdatePledgeResponse saveOrUpdatePledge(
        @WebParam(partName = "SaveOrUpdatePledgeRequest", name = "SaveOrUpdatePledgeRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        SaveOrUpdatePledgeRequest saveOrUpdatePledgeRequest
    );

    @WebResult(name = "GetCommunicationHistoryResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetCommunicationHistoryResponse")
    @WebMethod(operationName = "GetCommunicationHistory")
    public GetCommunicationHistoryResponse getCommunicationHistory(
        @WebParam(partName = "GetCommunicationHistoryRequest", name = "GetCommunicationHistoryRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetCommunicationHistoryRequest getCommunicationHistoryRequest
    );

    @WebResult(name = "GetPickListByNameResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetPickListByNameResponse")
    @WebMethod(operationName = "GetPickListByName")
    public GetPickListByNameResponse getPickListByName(
        @WebParam(partName = "GetPickListByNameRequest", name = "GetPickListByNameRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetPickListByNameRequest getPickListByNameRequest
    );

    @WebResult(name = "GetListMasterCustomTablesResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetListMasterCustomTablesResponse")
    @WebMethod(operationName = "GetListMasterCustomTables")
    public GetListMasterCustomTablesResponse getListMasterCustomTables(
        @WebParam(partName = "GetListMasterCustomTablesRequest", name = "GetListMasterCustomTablesRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetListMasterCustomTablesRequest getListMasterCustomTablesRequest
    );

    @WebResult(name = "GetPickListsCountResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetPickListsCountResponse")
    @WebMethod(operationName = "GetPickListsCount")
    public GetPickListsCountResponse getPickListsCount(
        @WebParam(partName = "GetPickListsCountRequest", name = "GetPickListsCountRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetPickListsCountRequest getPickListsCountRequest
    );

    @WebResult(name = "ReadCustomTableByIdResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "ReadCustomTableByIdResponse")
    @WebMethod(operationName = "ReadCustomTableById")
    public ReadCustomTableByIdResponse readCustomTableById(
        @WebParam(partName = "ReadCustomTableByIdRequest", name = "ReadCustomTableByIdRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        ReadCustomTableByIdRequest readCustomTableByIdRequest
    );

    @WebResult(name = "GetListDetailCustomTablesResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetListDetailCustomTablesResponse")
    @WebMethod(operationName = "GetListDetailCustomTables")
    public GetListDetailCustomTablesResponse getListDetailCustomTables(
        @WebParam(partName = "GetListDetailCustomTablesRequest", name = "GetListDetailCustomTablesRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetListDetailCustomTablesRequest getListDetailCustomTablesRequest
    );

    @WebResult(name = "GetConstituentPledgeResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetConstituentPledgeResponse")
    @WebMethod(operationName = "GetConstituentPledge")
    public GetConstituentPledgeResponse getConstituentPledge(
        @WebParam(partName = "GetConstituentPledgeRequest", name = "GetConstituentPledgeRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetConstituentPledgeRequest getConstituentPledgeRequest
    );

    @WebResult(name = "GetPickListsResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetPickListsResponse")
    @WebMethod(operationName = "GetPickLists")
    public GetPickListsResponse getPickLists(
        @WebParam(partName = "GetPickListsRequest", name = "GetPickListsRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetPickListsRequest getPickListsRequest
    );

    @WebResult(name = "GetCommunicationHistoryCountResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetCommunicationHistoryCountResponse")
    @WebMethod(operationName = "GetCommunicationHistoryCount")
    public GetCommunicationHistoryCountResponse getCommunicationHistoryCount(
        @WebParam(partName = "GetCommunicationHistoryCountRequest", name = "GetCommunicationHistoryCountRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetCommunicationHistoryCountRequest getCommunicationHistoryCountRequest
    );

    @WebResult(name = "GetSegmentationByNameResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetSegmentationByNameResponse")
    @WebMethod(operationName = "GetSegmentationByName")
    public GetSegmentationByNameResponse getSegmentationByName(
        @WebParam(partName = "GetSegmentationByNameRequest", name = "GetSegmentationByNameRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetSegmentationByNameRequest getSegmentationByNameRequest
    );

    @WebResult(name = "GetListEntityRelatedCustomTablesResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetListEntityRelatedCustomTablesResponse")
    @WebMethod(operationName = "GetListEntityRelatedCustomTables")
    public GetListEntityRelatedCustomTablesResponse getListEntityRelatedCustomTables(
        @WebParam(partName = "GetListEntityRelatedCustomTablesRequest", name = "GetListEntityRelatedCustomTablesRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetListEntityRelatedCustomTablesRequest getListEntityRelatedCustomTablesRequest
    );

    @WebResult(name = "SearchConstituentsResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "SearchConstituentsResponse")
    @WebMethod(operationName = "SearchConstituents")
    public SearchConstituentsResponse searchConstituents(
        @WebParam(partName = "SearchConstituentsRequest", name = "SearchConstituentsRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        SearchConstituentsRequest searchConstituentsRequest
    );

    @WebResult(name = "GetSegmentationByIdResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetSegmentationByIdResponse")
    @WebMethod(operationName = "GetSegmentationById")
    public GetSegmentationByIdResponse getSegmentationById(
        @WebParam(partName = "GetSegmentationByIdRequest", name = "GetSegmentationByIdRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetSegmentationByIdRequest getSegmentationByIdRequest
    );

    @WebResult(name = "ListCustomTablesResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "ListCustomTablesResponse")
    @WebMethod(operationName = "ListCustomTables")
    public ListCustomTablesResponse listCustomTables(
        @WebParam(partName = "ListCustomTablesRequest", name = "ListCustomTablesRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        ListCustomTablesRequest listCustomTablesRequest
    );

    @WebResult(name = "BulkAddCommunicationHistoryResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "BulkAddCommunicationHistoryResponse")
    @WebMethod(operationName = "BulkAddCommunicationHistory")
    public BulkAddCommunicationHistoryResponse bulkAddCommunicationHistory(
        @WebParam(partName = "BulkAddCommunicationHistoryRequest", name = "BulkAddCommunicationHistoryRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        BulkAddCommunicationHistoryRequest bulkAddCommunicationHistoryRequest
    );

    @WebResult(name = "GetConstituentByPaymentSourceResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetConstituentByPaymentSourceResponse")
    @WebMethod(operationName = "GetConstituentByPaymentSource")
    public GetConstituentByPaymentSourceResponse getConstituentByPaymentSource(
        @WebParam(partName = "GetConstituentByPaymentSourceRequest", name = "GetConstituentByPaymentSourceRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetConstituentByPaymentSourceRequest getConstituentByPaymentSourceRequest
    );

    @WebResult(name = "GetPostalCodesByRadiusResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetPostalCodesByRadiusResponse")
    @WebMethod(operationName = "GetPostalCodesByRadius")
    public GetPostalCodesByRadiusResponse getPostalCodesByRadius(
        @WebParam(partName = "GetPostalCodesByRadiusRequest", name = "GetPostalCodesByRadiusRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetPostalCodesByRadiusRequest getPostalCodesByRadiusRequest
    );

    @WebResult(name = "GetConstituentByListIdResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetConstituentByListIdResponse")
    @WebMethod(operationName = "GetConstituentByListId")
    public GetConstituentByListIdResponse getConstituentByListId(
        @WebParam(partName = "GetConstituentByListIdRequest", name = "GetConstituentByListIdRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetConstituentByListIdRequest getConstituentByListIdRequest
    );

    @WebResult(name = "AddCommunicationHistoryResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "AddCommunicationHistoryResponse")
    @WebMethod(operationName = "AddCommunicationHistory")
    public AddCommunicationHistoryResponse addCommunicationHistory(
        @WebParam(partName = "AddCommunicationHistoryRequest", name = "AddCommunicationHistoryRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        AddCommunicationHistoryRequest addCommunicationHistoryRequest
    );

    @WebResult(name = "SaveOrUpdateCustomTableRowResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "SaveOrUpdateCustomTableRowResponse")
    @WebMethod(operationName = "SaveOrUpdateCustomTableRow")
    public SaveOrUpdateCustomTableRowResponse saveOrUpdateCustomTableRow(
        @WebParam(partName = "SaveOrUpdateCustomTableRowRequest", name = "SaveOrUpdateCustomTableRowRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        SaveOrUpdateCustomTableRowRequest saveOrUpdateCustomTableRowRequest
    );

    @WebResult(name = "GetConstituentGiftResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetConstituentGiftResponse")
    @WebMethod(operationName = "GetConstituentGift")
    public GetConstituentGiftResponse getConstituentGift(
        @WebParam(partName = "GetConstituentGiftRequest", name = "GetConstituentGiftRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetConstituentGiftRequest getConstituentGiftRequest
    );

    @WebResult(name = "FindConstituentsResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "FindConstituentsResponse")
    @WebMethod(operationName = "FindConstituents")
    public FindConstituentsResponse findConstituents(
        @WebParam(partName = "FindConstituentsRequest", name = "FindConstituentsRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        FindConstituentsRequest findConstituentsRequest
    );

    @WebResult(name = "GetConstituentByIdResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetConstituentByIdResponse")
    @WebMethod(operationName = "GetConstituentById")
    public GetConstituentByIdResponse getConstituentById(
        @WebParam(partName = "GetConstituentByIdRequest", name = "GetConstituentByIdRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetConstituentByIdRequest getConstituentByIdRequest
    );

    @WebResult(name = "GetSegmentationListCountResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetSegmentationListCountResponse")
    @WebMethod(operationName = "GetSegmentationListCount")
    public GetSegmentationListCountResponse getSegmentationListCount(
        @WebParam(partName = "GetSegmentationListCountRequest", name = "GetSegmentationListCountRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetSegmentationListCountRequest getSegmentationListCountRequest
    );

    @WebResult(name = "GetSegmentationListByTypeCountResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetSegmentationListByTypeCountResponse")
    @WebMethod(operationName = "GetSegmentationListByTypeCount")
    public GetSegmentationListByTypeCountResponse getSegmentationListByTypeCount(
        @WebParam(partName = "GetSegmentationListByTypeCountRequest", name = "GetSegmentationListByTypeCountRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetSegmentationListByTypeCountRequest getSegmentationListByTypeCountRequest
    );

    @WebResult(name = "ReadCustomTableFieldByIdResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "ReadCustomTableFieldByIdResponse")
    @WebMethod(operationName = "ReadCustomTableFieldById")
    public ReadCustomTableFieldByIdResponse readCustomTableFieldById(
        @WebParam(partName = "ReadCustomTableFieldByIdRequest", name = "ReadCustomTableFieldByIdRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        ReadCustomTableFieldByIdRequest readCustomTableFieldByIdRequest
    );

    @WebResult(name = "GetPaymentSourcesByConstituentIdResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetPaymentSourcesByConstituentIdResponse")
    @WebMethod(operationName = "GetPaymentSourcesByConstituentId")
    public GetPaymentSourcesByConstituentIdResponse getPaymentSourcesByConstituentId(
        @WebParam(partName = "GetPaymentSourcesByConstituentIdRequest", name = "GetPaymentSourcesByConstituentIdRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetPaymentSourcesByConstituentIdRequest getPaymentSourcesByConstituentIdRequest
    );

    @WebResult(name = "GetSegmentationListResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetSegmentationListResponse")
    @WebMethod(operationName = "GetSegmentationList")
    public GetSegmentationListResponse getSegmentationList(
        @WebParam(partName = "GetSegmentationListRequest", name = "GetSegmentationListRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetSegmentationListRequest getSegmentationListRequest
    );

    @WebResult(name = "CreateDefaultConstituentResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "CreateDefaultConstituentResponse")
    @WebMethod(operationName = "CreateDefaultConstituent")
    public CreateDefaultConstituentResponse createDefaultConstituent(
        @WebParam(partName = "CreateDefaultConstituentRequest", name = "CreateDefaultConstituentRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        CreateDefaultConstituentRequest createDefaultConstituentRequest
    );

    @WebResult(name = "ReadCustomTableByNameResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "ReadCustomTableByNameResponse")
    @WebMethod(operationName = "ReadCustomTableByName")
    public ReadCustomTableByNameResponse readCustomTableByName(
        @WebParam(partName = "ReadCustomTableByNameRequest", name = "ReadCustomTableByNameRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        ReadCustomTableByNameRequest readCustomTableByNameRequest
    );

    @WebResult(name = "GetConstituentPledgeCountResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetConstituentPledgeCountResponse")
    @WebMethod(operationName = "GetConstituentPledgeCount")
    public GetConstituentPledgeCountResponse getConstituentPledgeCount(
        @WebParam(partName = "GetConstituentPledgeCountRequest", name = "GetConstituentPledgeCountRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetConstituentPledgeCountRequest getConstituentPledgeCountRequest
    );

    @WebResult(name = "GetDetailCustomTableRowsResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetDetailCustomTableRowsResponse")
    @WebMethod(operationName = "GetDetailCustomTableRows")
    public GetDetailCustomTableRowsResponse getDetailCustomTableRows(
        @WebParam(partName = "GetDetailCustomTableRowsRequest", name = "GetDetailCustomTableRowsRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetDetailCustomTableRowsRequest getDetailCustomTableRowsRequest
    );

    @WebResult(name = "GetConstituentRecurringGiftResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "GetConstituentRecurringGiftResponse")
    @WebMethod(operationName = "GetConstituentRecurringGift")
    public GetConstituentRecurringGiftResponse getConstituentRecurringGift(
        @WebParam(partName = "GetConstituentRecurringGiftRequest", name = "GetConstituentRecurringGiftRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        GetConstituentRecurringGiftRequest getConstituentRecurringGiftRequest
    );

    @WebResult(name = "SaveOrUpdateGiftResponse", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/", partName = "SaveOrUpdateGiftResponse")
    @WebMethod(operationName = "SaveOrUpdateGift")
    public SaveOrUpdateGiftResponse saveOrUpdateGift(
        @WebParam(partName = "SaveOrUpdateGiftRequest", name = "SaveOrUpdateGiftRequest", targetNamespace = "http://www.orangeleap.com/orangeleap/services3.0/")
        SaveOrUpdateGiftRequest saveOrUpdateGiftRequest
    );
}
