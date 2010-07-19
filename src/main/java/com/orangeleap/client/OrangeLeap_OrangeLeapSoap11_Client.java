
package com.orangeleap.client;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.1.9
 * Mon Jul 19 14:18:31 CDT 2010
 * Generated source version: 2.1.9
 * 
 */

public final class OrangeLeap_OrangeLeapSoap11_Client {

    private static final QName SERVICE_NAME = new QName("http://www.orangeleap.com/orangeleap/services3.0/", "OrangeLeapService");

    private OrangeLeap_OrangeLeapSoap11_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = OrangeLeapService.WSDL_LOCATION;
        if (args.length > 0) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        OrangeLeapService ss = new OrangeLeapService(wsdlURL, SERVICE_NAME);
        OrangeLeap port = ss.getOrangeLeapSoap11();  
        
        {
        System.out.println("Invoking listCustomTableFields...");
        com.orangeleap.client.ListCustomTableFieldsRequest _listCustomTableFields_listCustomTableFieldsRequest = null;
        com.orangeleap.client.ListCustomTableFieldsResponse _listCustomTableFields__return = port.listCustomTableFields(_listCustomTableFields_listCustomTableFieldsRequest);
        System.out.println("listCustomTableFields.result=" + _listCustomTableFields__return);


        }
        {
        System.out.println("Invoking saveOrUpdateConstituent...");
        com.orangeleap.client.SaveOrUpdateConstituentRequest _saveOrUpdateConstituent_saveOrUpdateConstituentRequest = null;
        com.orangeleap.client.SaveOrUpdateConstituentResponse _saveOrUpdateConstituent__return = port.saveOrUpdateConstituent(_saveOrUpdateConstituent_saveOrUpdateConstituentRequest);
        System.out.println("saveOrUpdateConstituent.result=" + _saveOrUpdateConstituent__return);


        }
        {
        System.out.println("Invoking saveOrUpdateRecurringGift...");
        com.orangeleap.client.SaveOrUpdateRecurringGiftRequest _saveOrUpdateRecurringGift_saveOrUpdateRecurringGiftRequest = null;
        com.orangeleap.client.SaveOrUpdateRecurringGiftResponse _saveOrUpdateRecurringGift__return = port.saveOrUpdateRecurringGift(_saveOrUpdateRecurringGift_saveOrUpdateRecurringGiftRequest);
        System.out.println("saveOrUpdateRecurringGift.result=" + _saveOrUpdateRecurringGift__return);


        }
        {
        System.out.println("Invoking getCustomTableRows...");
        com.orangeleap.client.GetCustomTableRowsRequest _getCustomTableRows_getCustomTableRowsRequest = null;
        com.orangeleap.client.GetCustomTableRowsResponse _getCustomTableRows__return = port.getCustomTableRows(_getCustomTableRows_getCustomTableRowsRequest);
        System.out.println("getCustomTableRows.result=" + _getCustomTableRows__return);


        }
        {
        System.out.println("Invoking getCustomTableRowCount...");
        com.orangeleap.client.GetCustomTableRowCountRequest _getCustomTableRowCount_getCustomTableRowCountRequest = null;
        com.orangeleap.client.GetCustomTableRowCountResponse _getCustomTableRowCount__return = port.getCustomTableRowCount(_getCustomTableRowCount_getCustomTableRowCountRequest);
        System.out.println("getCustomTableRowCount.result=" + _getCustomTableRowCount__return);


        }
        {
        System.out.println("Invoking getCustomTableTitleField...");
        com.orangeleap.client.GetCustomTableTitleFieldRequest _getCustomTableTitleField_getCustomTableTitleFieldRequest = null;
        com.orangeleap.client.GetCustomTableTitleFieldResponse _getCustomTableTitleField__return = port.getCustomTableTitleField(_getCustomTableTitleField_getCustomTableTitleFieldRequest);
        System.out.println("getCustomTableTitleField.result=" + _getCustomTableTitleField__return);


        }
        {
        System.out.println("Invoking addPickListItemByName...");
        com.orangeleap.client.AddPickListItemByNameRequest _addPickListItemByName_addPickListItemByNameRequest = null;
        com.orangeleap.client.AddPickListItemByNameResponse _addPickListItemByName__return = port.addPickListItemByName(_addPickListItemByName_addPickListItemByNameRequest);
        System.out.println("addPickListItemByName.result=" + _addPickListItemByName__return);


        }
        {
        System.out.println("Invoking getCustomTableConstituentContextField...");
        com.orangeleap.client.GetCustomTableConstituentContextFieldRequest _getCustomTableConstituentContextField_getCustomTableConstituentContextFieldRequest = null;
        com.orangeleap.client.GetCustomTableConstituentContextFieldResponse _getCustomTableConstituentContextField__return = port.getCustomTableConstituentContextField(_getCustomTableConstituentContextField_getCustomTableConstituentContextFieldRequest);
        System.out.println("getCustomTableConstituentContextField.result=" + _getCustomTableConstituentContextField__return);


        }
        {
        System.out.println("Invoking getConstituentRecurringGiftCount...");
        com.orangeleap.client.GetConstituentRecurringGiftCountRequest _getConstituentRecurringGiftCount_getConstituentRecurringGiftCountRequest = null;
        com.orangeleap.client.GetConstituentRecurringGiftCountResponse _getConstituentRecurringGiftCount__return = port.getConstituentRecurringGiftCount(_getConstituentRecurringGiftCount_getConstituentRecurringGiftCountRequest);
        System.out.println("getConstituentRecurringGiftCount.result=" + _getConstituentRecurringGiftCount__return);


        }
        {
        System.out.println("Invoking getSegmentationListByType...");
        com.orangeleap.client.GetSegmentationListByTypeRequest _getSegmentationListByType_getSegmentationListByTypeRequest = null;
        com.orangeleap.client.GetSegmentationListByTypeResponse _getSegmentationListByType__return = port.getSegmentationListByType(_getSegmentationListByType_getSegmentationListByTypeRequest);
        System.out.println("getSegmentationListByType.result=" + _getSegmentationListByType__return);


        }
        {
        System.out.println("Invoking readCustomTableRow...");
        com.orangeleap.client.ReadCustomTableRowRequest _readCustomTableRow_readCustomTableRowRequest = null;
        com.orangeleap.client.ReadCustomTableRowResponse _readCustomTableRow__return = port.readCustomTableRow(_readCustomTableRow_readCustomTableRowRequest);
        System.out.println("readCustomTableRow.result=" + _readCustomTableRow__return);


        }
        {
        System.out.println("Invoking getConstituentGiftCount...");
        com.orangeleap.client.GetConstituentGiftCountRequest _getConstituentGiftCount_getConstituentGiftCountRequest = null;
        com.orangeleap.client.GetConstituentGiftCountResponse _getConstituentGiftCount__return = port.getConstituentGiftCount(_getConstituentGiftCount_getConstituentGiftCountRequest);
        System.out.println("getConstituentGiftCount.result=" + _getConstituentGiftCount__return);


        }
        {
        System.out.println("Invoking saveOrUpdatePledge...");
        com.orangeleap.client.SaveOrUpdatePledgeRequest _saveOrUpdatePledge_saveOrUpdatePledgeRequest = null;
        com.orangeleap.client.SaveOrUpdatePledgeResponse _saveOrUpdatePledge__return = port.saveOrUpdatePledge(_saveOrUpdatePledge_saveOrUpdatePledgeRequest);
        System.out.println("saveOrUpdatePledge.result=" + _saveOrUpdatePledge__return);


        }
        {
        System.out.println("Invoking getCommunicationHistory...");
        com.orangeleap.client.GetCommunicationHistoryRequest _getCommunicationHistory_getCommunicationHistoryRequest = null;
        com.orangeleap.client.GetCommunicationHistoryResponse _getCommunicationHistory__return = port.getCommunicationHistory(_getCommunicationHistory_getCommunicationHistoryRequest);
        System.out.println("getCommunicationHistory.result=" + _getCommunicationHistory__return);


        }
        {
        System.out.println("Invoking getPickListByName...");
        com.orangeleap.client.GetPickListByNameRequest _getPickListByName_getPickListByNameRequest = null;
        com.orangeleap.client.GetPickListByNameResponse _getPickListByName__return = port.getPickListByName(_getPickListByName_getPickListByNameRequest);
        System.out.println("getPickListByName.result=" + _getPickListByName__return);


        }
        {
        System.out.println("Invoking getListMasterCustomTables...");
        com.orangeleap.client.GetListMasterCustomTablesRequest _getListMasterCustomTables_getListMasterCustomTablesRequest = null;
        com.orangeleap.client.GetListMasterCustomTablesResponse _getListMasterCustomTables__return = port.getListMasterCustomTables(_getListMasterCustomTables_getListMasterCustomTablesRequest);
        System.out.println("getListMasterCustomTables.result=" + _getListMasterCustomTables__return);


        }
        {
        System.out.println("Invoking getPickListsCount...");
        com.orangeleap.client.GetPickListsCountRequest _getPickListsCount_getPickListsCountRequest = null;
        com.orangeleap.client.GetPickListsCountResponse _getPickListsCount__return = port.getPickListsCount(_getPickListsCount_getPickListsCountRequest);
        System.out.println("getPickListsCount.result=" + _getPickListsCount__return);


        }
        {
        System.out.println("Invoking readCustomTableById...");
        com.orangeleap.client.ReadCustomTableByIdRequest _readCustomTableById_readCustomTableByIdRequest = null;
        com.orangeleap.client.ReadCustomTableByIdResponse _readCustomTableById__return = port.readCustomTableById(_readCustomTableById_readCustomTableByIdRequest);
        System.out.println("readCustomTableById.result=" + _readCustomTableById__return);


        }
        {
        System.out.println("Invoking getListDetailCustomTables...");
        com.orangeleap.client.GetListDetailCustomTablesRequest _getListDetailCustomTables_getListDetailCustomTablesRequest = null;
        com.orangeleap.client.GetListDetailCustomTablesResponse _getListDetailCustomTables__return = port.getListDetailCustomTables(_getListDetailCustomTables_getListDetailCustomTablesRequest);
        System.out.println("getListDetailCustomTables.result=" + _getListDetailCustomTables__return);


        }
        {
        System.out.println("Invoking getConstituentPledge...");
        com.orangeleap.client.GetConstituentPledgeRequest _getConstituentPledge_getConstituentPledgeRequest = null;
        com.orangeleap.client.GetConstituentPledgeResponse _getConstituentPledge__return = port.getConstituentPledge(_getConstituentPledge_getConstituentPledgeRequest);
        System.out.println("getConstituentPledge.result=" + _getConstituentPledge__return);


        }
        {
        System.out.println("Invoking getPickLists...");
        com.orangeleap.client.GetPickListsRequest _getPickLists_getPickListsRequest = null;
        com.orangeleap.client.GetPickListsResponse _getPickLists__return = port.getPickLists(_getPickLists_getPickListsRequest);
        System.out.println("getPickLists.result=" + _getPickLists__return);


        }
        {
        System.out.println("Invoking getCommunicationHistoryCount...");
        com.orangeleap.client.GetCommunicationHistoryCountRequest _getCommunicationHistoryCount_getCommunicationHistoryCountRequest = null;
        com.orangeleap.client.GetCommunicationHistoryCountResponse _getCommunicationHistoryCount__return = port.getCommunicationHistoryCount(_getCommunicationHistoryCount_getCommunicationHistoryCountRequest);
        System.out.println("getCommunicationHistoryCount.result=" + _getCommunicationHistoryCount__return);


        }
        {
        System.out.println("Invoking getSegmentationByName...");
        com.orangeleap.client.GetSegmentationByNameRequest _getSegmentationByName_getSegmentationByNameRequest = null;
        com.orangeleap.client.GetSegmentationByNameResponse _getSegmentationByName__return = port.getSegmentationByName(_getSegmentationByName_getSegmentationByNameRequest);
        System.out.println("getSegmentationByName.result=" + _getSegmentationByName__return);


        }
        {
        System.out.println("Invoking getListEntityRelatedCustomTables...");
        com.orangeleap.client.GetListEntityRelatedCustomTablesRequest _getListEntityRelatedCustomTables_getListEntityRelatedCustomTablesRequest = null;
        com.orangeleap.client.GetListEntityRelatedCustomTablesResponse _getListEntityRelatedCustomTables__return = port.getListEntityRelatedCustomTables(_getListEntityRelatedCustomTables_getListEntityRelatedCustomTablesRequest);
        System.out.println("getListEntityRelatedCustomTables.result=" + _getListEntityRelatedCustomTables__return);


        }
        {
        System.out.println("Invoking searchConstituents...");
        com.orangeleap.client.SearchConstituentsRequest _searchConstituents_searchConstituentsRequest = null;
        com.orangeleap.client.SearchConstituentsResponse _searchConstituents__return = port.searchConstituents(_searchConstituents_searchConstituentsRequest);
        System.out.println("searchConstituents.result=" + _searchConstituents__return);


        }
        {
        System.out.println("Invoking getSegmentationById...");
        com.orangeleap.client.GetSegmentationByIdRequest _getSegmentationById_getSegmentationByIdRequest = null;
        com.orangeleap.client.GetSegmentationByIdResponse _getSegmentationById__return = port.getSegmentationById(_getSegmentationById_getSegmentationByIdRequest);
        System.out.println("getSegmentationById.result=" + _getSegmentationById__return);


        }
        {
        System.out.println("Invoking listCustomTables...");
        com.orangeleap.client.ListCustomTablesRequest _listCustomTables_listCustomTablesRequest = null;
        com.orangeleap.client.ListCustomTablesResponse _listCustomTables__return = port.listCustomTables(_listCustomTables_listCustomTablesRequest);
        System.out.println("listCustomTables.result=" + _listCustomTables__return);


        }
        {
        System.out.println("Invoking bulkAddCommunicationHistory...");
        com.orangeleap.client.BulkAddCommunicationHistoryRequest _bulkAddCommunicationHistory_bulkAddCommunicationHistoryRequest = null;
        com.orangeleap.client.BulkAddCommunicationHistoryResponse _bulkAddCommunicationHistory__return = port.bulkAddCommunicationHistory(_bulkAddCommunicationHistory_bulkAddCommunicationHistoryRequest);
        System.out.println("bulkAddCommunicationHistory.result=" + _bulkAddCommunicationHistory__return);


        }
        {
        System.out.println("Invoking getConstituentByPaymentSource...");
        com.orangeleap.client.GetConstituentByPaymentSourceRequest _getConstituentByPaymentSource_getConstituentByPaymentSourceRequest = null;
        com.orangeleap.client.GetConstituentByPaymentSourceResponse _getConstituentByPaymentSource__return = port.getConstituentByPaymentSource(_getConstituentByPaymentSource_getConstituentByPaymentSourceRequest);
        System.out.println("getConstituentByPaymentSource.result=" + _getConstituentByPaymentSource__return);


        }
        {
        System.out.println("Invoking getPostalCodesByRadius...");
        com.orangeleap.client.GetPostalCodesByRadiusRequest _getPostalCodesByRadius_getPostalCodesByRadiusRequest = null;
        com.orangeleap.client.GetPostalCodesByRadiusResponse _getPostalCodesByRadius__return = port.getPostalCodesByRadius(_getPostalCodesByRadius_getPostalCodesByRadiusRequest);
        System.out.println("getPostalCodesByRadius.result=" + _getPostalCodesByRadius__return);


        }
        {
        System.out.println("Invoking getConstituentByListId...");
        com.orangeleap.client.GetConstituentByListIdRequest _getConstituentByListId_getConstituentByListIdRequest = null;
        com.orangeleap.client.GetConstituentByListIdResponse _getConstituentByListId__return = port.getConstituentByListId(_getConstituentByListId_getConstituentByListIdRequest);
        System.out.println("getConstituentByListId.result=" + _getConstituentByListId__return);


        }
        {
        System.out.println("Invoking addCommunicationHistory...");
        com.orangeleap.client.AddCommunicationHistoryRequest _addCommunicationHistory_addCommunicationHistoryRequest = null;
        com.orangeleap.client.AddCommunicationHistoryResponse _addCommunicationHistory__return = port.addCommunicationHistory(_addCommunicationHistory_addCommunicationHistoryRequest);
        System.out.println("addCommunicationHistory.result=" + _addCommunicationHistory__return);


        }
        {
        System.out.println("Invoking saveOrUpdateCustomTableRow...");
        com.orangeleap.client.SaveOrUpdateCustomTableRowRequest _saveOrUpdateCustomTableRow_saveOrUpdateCustomTableRowRequest = null;
        com.orangeleap.client.SaveOrUpdateCustomTableRowResponse _saveOrUpdateCustomTableRow__return = port.saveOrUpdateCustomTableRow(_saveOrUpdateCustomTableRow_saveOrUpdateCustomTableRowRequest);
        System.out.println("saveOrUpdateCustomTableRow.result=" + _saveOrUpdateCustomTableRow__return);


        }
        {
        System.out.println("Invoking getConstituentGift...");
        com.orangeleap.client.GetConstituentGiftRequest _getConstituentGift_getConstituentGiftRequest = null;
        com.orangeleap.client.GetConstituentGiftResponse _getConstituentGift__return = port.getConstituentGift(_getConstituentGift_getConstituentGiftRequest);
        System.out.println("getConstituentGift.result=" + _getConstituentGift__return);


        }
        {
        System.out.println("Invoking findConstituents...");
        com.orangeleap.client.FindConstituentsRequest _findConstituents_findConstituentsRequest = null;
        com.orangeleap.client.FindConstituentsResponse _findConstituents__return = port.findConstituents(_findConstituents_findConstituentsRequest);
        System.out.println("findConstituents.result=" + _findConstituents__return);


        }
        {
        System.out.println("Invoking getConstituentById...");
        com.orangeleap.client.GetConstituentByIdRequest _getConstituentById_getConstituentByIdRequest = null;
        com.orangeleap.client.GetConstituentByIdResponse _getConstituentById__return = port.getConstituentById(_getConstituentById_getConstituentByIdRequest);
        System.out.println("getConstituentById.result=" + _getConstituentById__return);


        }
        {
        System.out.println("Invoking getSegmentationListCount...");
        com.orangeleap.client.GetSegmentationListCountRequest _getSegmentationListCount_getSegmentationListCountRequest = null;
        com.orangeleap.client.GetSegmentationListCountResponse _getSegmentationListCount__return = port.getSegmentationListCount(_getSegmentationListCount_getSegmentationListCountRequest);
        System.out.println("getSegmentationListCount.result=" + _getSegmentationListCount__return);


        }
        {
        System.out.println("Invoking getSegmentationListByTypeCount...");
        com.orangeleap.client.GetSegmentationListByTypeCountRequest _getSegmentationListByTypeCount_getSegmentationListByTypeCountRequest = null;
        com.orangeleap.client.GetSegmentationListByTypeCountResponse _getSegmentationListByTypeCount__return = port.getSegmentationListByTypeCount(_getSegmentationListByTypeCount_getSegmentationListByTypeCountRequest);
        System.out.println("getSegmentationListByTypeCount.result=" + _getSegmentationListByTypeCount__return);


        }
        {
        System.out.println("Invoking readCustomTableFieldById...");
        com.orangeleap.client.ReadCustomTableFieldByIdRequest _readCustomTableFieldById_readCustomTableFieldByIdRequest = null;
        com.orangeleap.client.ReadCustomTableFieldByIdResponse _readCustomTableFieldById__return = port.readCustomTableFieldById(_readCustomTableFieldById_readCustomTableFieldByIdRequest);
        System.out.println("readCustomTableFieldById.result=" + _readCustomTableFieldById__return);


        }
        {
        System.out.println("Invoking getPaymentSourcesByConstituentId...");
        com.orangeleap.client.GetPaymentSourcesByConstituentIdRequest _getPaymentSourcesByConstituentId_getPaymentSourcesByConstituentIdRequest = null;
        com.orangeleap.client.GetPaymentSourcesByConstituentIdResponse _getPaymentSourcesByConstituentId__return = port.getPaymentSourcesByConstituentId(_getPaymentSourcesByConstituentId_getPaymentSourcesByConstituentIdRequest);
        System.out.println("getPaymentSourcesByConstituentId.result=" + _getPaymentSourcesByConstituentId__return);


        }
        {
        System.out.println("Invoking getSegmentationList...");
        com.orangeleap.client.GetSegmentationListRequest _getSegmentationList_getSegmentationListRequest = null;
        com.orangeleap.client.GetSegmentationListResponse _getSegmentationList__return = port.getSegmentationList(_getSegmentationList_getSegmentationListRequest);
        System.out.println("getSegmentationList.result=" + _getSegmentationList__return);


        }
        {
        System.out.println("Invoking createDefaultConstituent...");
        com.orangeleap.client.CreateDefaultConstituentRequest _createDefaultConstituent_createDefaultConstituentRequest = null;
        com.orangeleap.client.CreateDefaultConstituentResponse _createDefaultConstituent__return = port.createDefaultConstituent(_createDefaultConstituent_createDefaultConstituentRequest);
        System.out.println("createDefaultConstituent.result=" + _createDefaultConstituent__return);


        }
        {
        System.out.println("Invoking readCustomTableByName...");
        com.orangeleap.client.ReadCustomTableByNameRequest _readCustomTableByName_readCustomTableByNameRequest = null;
        com.orangeleap.client.ReadCustomTableByNameResponse _readCustomTableByName__return = port.readCustomTableByName(_readCustomTableByName_readCustomTableByNameRequest);
        System.out.println("readCustomTableByName.result=" + _readCustomTableByName__return);


        }
        {
        System.out.println("Invoking getConstituentPledgeCount...");
        com.orangeleap.client.GetConstituentPledgeCountRequest _getConstituentPledgeCount_getConstituentPledgeCountRequest = null;
        com.orangeleap.client.GetConstituentPledgeCountResponse _getConstituentPledgeCount__return = port.getConstituentPledgeCount(_getConstituentPledgeCount_getConstituentPledgeCountRequest);
        System.out.println("getConstituentPledgeCount.result=" + _getConstituentPledgeCount__return);


        }
        {
        System.out.println("Invoking getDetailCustomTableRows...");
        com.orangeleap.client.GetDetailCustomTableRowsRequest _getDetailCustomTableRows_getDetailCustomTableRowsRequest = null;
        com.orangeleap.client.GetDetailCustomTableRowsResponse _getDetailCustomTableRows__return = port.getDetailCustomTableRows(_getDetailCustomTableRows_getDetailCustomTableRowsRequest);
        System.out.println("getDetailCustomTableRows.result=" + _getDetailCustomTableRows__return);


        }
        {
        System.out.println("Invoking getConstituentRecurringGift...");
        com.orangeleap.client.GetConstituentRecurringGiftRequest _getConstituentRecurringGift_getConstituentRecurringGiftRequest = null;
        com.orangeleap.client.GetConstituentRecurringGiftResponse _getConstituentRecurringGift__return = port.getConstituentRecurringGift(_getConstituentRecurringGift_getConstituentRecurringGiftRequest);
        System.out.println("getConstituentRecurringGift.result=" + _getConstituentRecurringGift__return);


        }
        {
        System.out.println("Invoking saveOrUpdateGift...");
        com.orangeleap.client.SaveOrUpdateGiftRequest _saveOrUpdateGift_saveOrUpdateGiftRequest = null;
        com.orangeleap.client.SaveOrUpdateGiftResponse _saveOrUpdateGift__return = port.saveOrUpdateGift(_saveOrUpdateGift_saveOrUpdateGiftRequest);
        System.out.println("saveOrUpdateGift.result=" + _saveOrUpdateGift__return);


        }

        System.exit(0);
    }

}
