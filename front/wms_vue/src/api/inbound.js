import axios from "axios";

//const API_BASE_URL = "http://192.168.43.128:8000";
const API_BASE_URL = "http://192.168.136.86:8000";
//const API_BASE_URL = "http://localhost:8000";
//const API_BASE_URL = "https://e1797793-87d4-4690-b69a-a91ae37d8a0e.mock.pstmn.io";

export const getInboundList = () => {
  return axios.get(`${API_BASE_URL}/getInbound`);
};

export const getSupplierNames = () => {
  return axios.get(`${API_BASE_URL}/wmsSupplier/getSupplierName`);
};

export const deleteInboundItem = (id) => {
  return axios.delete(`${API_BASE_URL}/wmsInbound/deleteInboundById?id=${id}`);
};
export const getInboundItemInfo = (id) => {
  return axios.post(`${API_BASE_URL}/wmsInboundDetail/inboundItemsInfo`, {
    id,
  });
};
export const fetchPartsByid = (id) => {
  return axios.post(`${API_BASE_URL}/wmsInboundDetail/inboundItemsInfo`, {
    id,
  });
};
export const saveform2 = (singleDetail) => {
  return axios.post(
    `${API_BASE_URL}/wmsInboundDetail/addRealQuantity?ibdId=${singleDetail.id}&quantity=${singleDetail.realQuantity}`
  );
};

export const saveOrUpdateItem = async (item) => {
  try {
    console.log("Sending item:", item);
    const response = await axios.post(`${API_BASE_URL}/api/items`, item);
    return response.data;
  } catch (error) {
    console.error("Error saving or updating item:", error);
    throw error; // 重新抛出错误，以便调用方处理
  }
};

export const fetchItems = async () => {
  try {
    const response = await axios.get(`${API_BASE_URL}/wmsItem`);
    return response.data;
  } catch (error) {
    console.error("Error fetching items:", error);
    throw error; // 重新抛出错误，以便调用方处理
  }
};

export const fetchItemById = async (id) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/wmsItem/${id}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching item by ID:", error);
    throw error; // 重新抛出错误，以便调用方处理
  }
};
export const getItemsBySupplier = async (supplierId) => {
  try {
    const response = await axios.get(
      `${API_BASE_URL}/wmsItem/itemsBySupplier?supplierId=${supplierId}`
    );
    return response.data;
  } catch (error) {
    console.error("Error fetching item by ID:", error);
    throw error; // 重新抛出错误，以便调用方处理
  }
};

export const getBox = async (formData) => {
  try {
    console.log("Saving form data:", formData);
    const response = await axios.get(
      `${API_BASE_URL}/wmsContainer/containerCount`,
      {
        params: formData,
      }
    );
    return response.data;
  } catch (error) {
    console.error("Error saving form data:", error);
    throw error;
  }
};

export const getNames = async (formData) => {
  try {
    console.log("Saving form data:", formData);
    const response = await axios.get(
      `${API_BASE_URL}/wmsSupplier/names`,
      formData
    );
    return response.data;
  } catch (error) {
    console.error("Error saving form data:", error);
    throw error; // 重新抛出错误，以便调用方处理
  }
};

export const getSupplierIdByName = async (supplierName) => {
  try {
    const response = await axios.get(
      `${API_BASE_URL}/wmsSupplier/idByName?supplierName=${supplierName}`
    );
    return response.data;
  } catch (error) {
    console.error("Error fetching supplier ID by name:", error);
    throw error;
  }
};
export const saveForm = async (formData) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/inbound`, formData);
    return response.data;
  } catch (error) {
    console.error("Error in save", error);
    throw error;
  }
};
