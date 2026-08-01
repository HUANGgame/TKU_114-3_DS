# Test Cases

本文件記錄 0730 程式的測試案例。每個案例都包含輸入、操作、預期結果、實際結果與通過狀態。

## 測試案例表

| 編號 | 對應檔案 | 輸入 | 操作 | 預期結果 | 實際結果 | 狀態 |
| --- | --- | --- | --- | --- | --- | --- |
| 1 | `MergeArrayPractice.java` | A = `{1, 3, 5}`, B = `{2, 4, 6}` | 合併兩個已排序陣列 | 輸出 `{1, 2, 3, 4, 5, 6}` | 結果排序正確，所有元素只出現一次 | 通過 |
| 2 | `MergeArrayPractice.java` | A = `{}`, B = `{-3, 0, 8}` | 測試一個陣列為空 | 輸出 `{-3, 0, 8}` | 空陣列可正確合併 | 通過 |
| 3 | `MergeArrayPractice.java` | A = `{-5, 2, 2}`, B = `{-5, 3}` | 測試負數與重複值 | 輸出 `{-5, -5, 2, 2, 3}` | 負數與重複值保留正確 | 通過 |
| 4 | `MergeSortPractice.java` | `{41, 12, 35, 8, 27, 19, 50, 3}` | 執行 `mergeSort()` | 每次分割與合併後區間內容正確，最後升冪 | 停止條件與合併流程正確 | 通過 |
| 5 | `MergeSortPractice.java` | `{}` | 測試空資料 | 不發生錯誤，輸出空陣列 | 空資料能停止處理 | 通過 |
| 6 | `MergeSortPractice.java` | `{7}` | 測試單筆資料 | 不需分割，仍為 `{7}` | 單筆資料能停止處理 | 通過 |
| 7 | `InventorySearchPractice.java` | 12 筆未排序庫存編號 | 先歸併排序，再二分查找第一筆 | 找到排序後第一筆索引 | 排序後可查到第一筆 | 通過 |
| 8 | `InventorySearchPractice.java` | 12 筆未排序庫存編號 | 查詢排序後最後一筆 | 找到最後一筆索引 | 排序與搜尋鍵一致 | 通過 |
| 9 | `InventorySearchPractice.java` | 不存在的庫存編號 | 二分查找 | 回傳找不到訊息，不使用 `-1` 存取陣列 | 顯示找不到資料 | 通過 |
| 10 | `OrderManagementPractice.java` | 空等待佇列 | 顯示下一筆待處理訂單 | 顯示空佇列或沒有待處理訂單 | 空佇列處理正確 | 通過 |
| 11 | `OrderManagementPractice.java` | 重複訂單編號 | 新增訂單 | 拒絕重複編號 | 重複編號不加入主資料 | 通過 |
| 12 | `OrderManagementPractice.java` | 相同顧客姓名的多筆訂單 | 依顧客姓名搜尋全部訂單 | 顯示所有符合訂單 | 順序查找可找出多筆 | 通過 |
| 13 | `LibraryManagementSystem.java` | 空書籍清單 | 查詢與排序 | 不發生錯誤，顯示空資料處理訊息 | 空資料處理正確 | 通過 |
| 14 | `LibraryManagementSystem.java` | 重複書籍編號 | 新增書籍 | 拒絕重複編號 | 主資料沒有新增重複項目 | 通過 |
| 15 | `LibraryManagementSystem.java` | 借閱次數相同的書籍 | 依借閱次數降冪排序 | 借閱次數高者在前，資料欄位完整 | 排序後欄位保持對應 | 通過 |
| 16 | `RepairSchedulingSystem.java` | 空等待維修 Queue | 執行完成下一筆維修 | 回傳沒有待處理工作 | 空 Queue 不會造成錯誤 | 通過 |
| 17 | `RepairSchedulingSystem.java` | 空完成 Stack | 執行復原最近完成工作 | 回傳沒有完成記錄可復原 | 空 Stack 不會造成錯誤 | 通過 |
| 18 | `RepairSchedulingSystem.java` | 優先等級相同的維修工作 | 執行 `mergeSortByPriorityDescending()` | 優先等級降冪，相同等級保持原順序 | 穩定排序正確 | 通過 |
| 19 | `EventRegistrationSystem.java` | 報名人數超過 `CAPACITY` | 新增報名 | 超過名額者進入候補 Queue | 額滿資料加入候補佇列 | 通過 |
| 20 | `EventRegistrationSystem.java` | 重複編號 `E101` | 執行 `addRegistration()` | 顯示重複編號並拒絕新增 | 重複編號未加入 `allRegistrations` | 通過 |
| 21 | `EventRegistrationSystem.java` | 不存在編號 `E777` | 執行 `cancelRegistration()` | 顯示取消失敗，不修改 Stack | 取消不存在資料處理正確 | 通過 |
| 22 | `EventRegistrationSystem.java` | 空候補佇列 | 執行 `fillNextFromWaiting()` | 顯示候補佇列為空 | 空候補佇列處理正確 | 通過 |
| 23 | `EventRegistrationSystem.java` | 姓名 `Amy` | 執行 `searchName()` | 顯示所有姓名為 Amy 的報名資料 | 順序查找找到多筆資料 | 通過 |
| 24 | `AlgorithmComparisonReport.java` | 16、128、1024 筆資料 | 比較三種排序比較次數 | 輸出已排序、反向、固定亂序表格 | 表格與觀察結論已輸出 | 通過 |

## 多重操作測試

| 編號 | 對應檔案 | 輸入 | 操作 | 預期結果 | 實際結果 | 狀態 |
| --- | --- | --- | --- | --- | --- | --- |
| 25 | `EventRegistrationSystem.java` | `E103, E101, E105, E102, E104` | 新增、額滿候補、取消 `E101`、候補遞補、查詢 | 主資料、候補 Queue、取消 Stack 數量一致 | 統計顯示全部 5 筆、正式 3 筆、候補 1 筆、取消 1 筆 | 通過 |
| 26 | `RepairSchedulingSystem.java` | 6 筆維修工作 | 新增、完成兩筆、復原一筆、排序、搜尋 | Queue、Stack、ArrayList 狀態一致 | 等待、完成與全部統計正確 | 通過 |
| 27 | `OrderManagementPractice.java` | 多筆訂單 | 新增、排序、完成、查詢、重複編號測試 | 主資料、等待佇列、完成堆疊責任清楚 | 操作後狀態一致 | 通過 |

## 常見錯誤與診斷檢查

| 診斷項目 | 檢查結果 | 狀態 |
| --- | --- | --- |
| 歸併排序有 `left >= right` 停止條件 | `RegistrationAlgorithms.java`、`RepairAlgorithms.java`、`BookAlgorithms.java` 均有停止條件 | 通過 |
| 左右區間沒有重疊 | 使用 `left..mid` 與 `mid + 1..right` | 通過 |
| 合併後有複製回原陣列或原集合 | 合併完成後以迴圈寫回原資料 | 通過 |
| 合併索引從目前區間開始 | `output` 從 `left` 開始，不覆蓋區間外資料 | 通過 |
| 鍵相同時保持穩定性 | 使用 `<=` 或 `>=` 讓左側資料先進入結果 | 通過 |
| 二分查找只用於已排序資料 | `searchId()` 先依編號排序後才呼叫二分查找 | 通過 |
| 排序鍵與搜尋鍵一致 | 編號查詢使用編號排序，庫存查詢使用庫存編號排序 | 通過 |
| Queue 的處理順序未被排序破壞 | 排序使用副本，不直接排序等待佇列 | 通過 |
| 主資料集合定義清楚 | 報名系統使用 `allRegistrations`，維修系統使用 `allTasks` | 通過 |
| Stack 復原流程會回到等待佇列 | 維修系統復原完成工作後放回等待佇列前端 | 通過 |
| 多檔案放在同一資料夾 | 0730 相關 `.java` 檔均放在 `0730` 資料夾 | 通過 |
| public class 與檔名一致 | 新增檔案皆符合檔名與 public class 一致 | 通過 |

## 未通過項目與修正

目前測試案例皆通過，沒有未通過項目。

若後續出現未通過項目，應在此區補上：
- 未通過案例編號
- 錯誤原因
- 修改內容
- 重新測試結果

## 總結

本次測試涵蓋空資料、單筆資料、重複資料、邊界資料、缺乏資料及多重操作。排序測試確認歸併排序的分割、停止、合併與複製流程；搜尋測試確認二分查找只用於已排序資料，順序查找用於未排序或多筆符合資料。系統測試確認 `ArrayList`、`Queue`、`Stack` 的責任分工一致。
