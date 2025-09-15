import axios from "axios";

const API_BASE = "/api/products";

// Get all products
export const fetchProducts = async (delay = false) => {
  const url = delay ? `${API_BASE}?delay=true` : API_BASE;
  const response = await axios.get(url);
  return response.data;
};

// Get product by ID
export const fetchProductById = async (id, delay = false) => {
  const url = delay ? `${API_BASE}/${id}?delay=true` : `${API_BASE}/${id}`;
  const response = await axios.get(url);
  return response.data;
};

export async function addProduct(product) {
  const res = await fetch("/api/products", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(product),
  });
  if (!res.ok) throw new Error("Failed to add product");
  return await res.json();
}
