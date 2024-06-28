import { fileURLToPath, URL } from "node:url";

import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  devServer: {
    https: false,
    hotOnly: false,
    proxy: {
      "/api": {
        target: "https://907efcd7-7787-4f32-847a-d3e494f7f3a9.mock.pstmn.io",
        changeOrigin: true,
        pathRewrite: {
          "^/api": "",
        },
      },
    },
  },
  resolve: {
    alias: {
      "@": fileURLToPath(new URL("./src", import.meta.url)),
    },
  },
});
