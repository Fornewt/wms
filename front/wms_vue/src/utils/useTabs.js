import { ref } from "vue";
import { useRouter, useRoute } from "vue-router";

const tabs = ref([{ name: "/receiptorder", label: "入库单" }]);

const activeTab = ref("/receiptorder");

const useTabs = () => {
  const router = useRouter();
  const route = useRoute();

  const handleTabClick = (tab) => {
    console.log("标签点击:", tab); // 调试日志
    const tabName = tab.props?.name; // 获取标签名
    console.log("标签名:", tabName); // 检查标签名是否正确
    if (tabName) {
      activeTab.value = tabName;
      router.push(tabName).catch((err) => {
        console.error("导航错误:", err);
      });
      console.log("标签切换到:", tabName);
    } else {
      console.error("无效的标签对象:", tab);
    }
  };

  const removeTab = (tabName) => {
    const tabIndex = tabs.value.findIndex((tab) => tab.name === tabName);
    if (tabIndex !== -1) {
      tabs.value.splice(tabIndex, 1);
      if (activeTab.value === tabName && tabs.value.length > 0) {
        activeTab.value = tabs.value[Math.max(tabIndex - 1, 0)].name;
        router.push(activeTab.value);
        console.log("删除标签");
      } else if (tabs.value.length === 0) {
        activeTab.value = "/receiptorder";
        router.push("/");
      }
    }
  };

  const addTab = (path, label) => {
    if (!tabs.value.some((tab) => tab.name === path)) {
      tabs.value.push({ name: path, label });
    }
    activeTab.value = path;
    router.push(path).catch((err) => {
      console.error("导航错误:", err);
    });
    console.log("增加标签");
  };

  return {
    tabs,
    activeTab,
    handleTabClick,
    removeTab,
    addTab,
  };
};

export default useTabs;
