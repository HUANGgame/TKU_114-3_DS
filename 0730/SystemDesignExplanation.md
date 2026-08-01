# System Design Explanation

本文件以 0730 當天完成的系統作為說明來源，對照實際檔名與方法名稱，整理資料結構與演算法的選擇原因。

## 1. 保存全部報名資料

實際方案：
- `EventRegistrationSystem.java`
- 欄位：`allRegistrations`
- 方法：`addRegistration(String id, String name)`

使用資料結構：
- `ArrayList<Registration>`

選擇原因：
- 報名資料需要完整保留，後續要排序、查詢、統計與列印全部資料。
- `ArrayList` 適合依序保存多筆資料，也方便用 index 存取與傳入排序方法。

未採用原因：
- 不使用 `Queue`，因為 Queue 只適合先進先出的候補流程，不適合保存全部歷史資料。
- 不使用 `Stack`，因為 Stack 只適合最近取消記錄，不適合完整查詢全部報名資料。

## 2. 候補順序處理

實際方案：
- `EventRegistrationSystem.java`
- 欄位：`waitingQueue`
- 方法：`addRegistration(String id, String name)`
- 方法：`fillNextFromWaiting()`

使用資料結構：
- `Queue`
- 實作類別：`ArrayDeque<Registration>`

選擇原因：
- 候補名單必須依照加入順序處理，最早候補的人應該最先遞補。
- Queue 的 `offer()` 與 `poll()` 正好符合先進先出。

未採用原因：
- 不使用 `Stack`，因為 Stack 會讓最後候補的人先遞補，順序不公平。
- 不只使用 `ArrayList`，因為每次取第一位候補時會需要額外管理索引或刪除位置。

## 3. 最近取消記錄

實際方案：
- `EventRegistrationSystem.java`
- 欄位：`cancelledStack`
- 方法：`cancelRegistration(String id)`
- 方法：`printCancelledStack()`

使用資料結構：
- `Stack`
- 實作類別：`ArrayDeque<Registration>`

選擇原因：
- 取消記錄常見需求是查看最近一筆或最近多筆。
- Stack 的 `push()` 可把最新取消資料放在最上方。

未採用原因：
- 不使用 `Queue`，因為 Queue 會先取出最早取消的資料，不符合最近記錄需求。
- 不只使用 `ArrayList`，因為要模擬最近取消順序時需要自行控制最後位置。

## 4. 依報名順序排序

實際方案：
- `EventRegistrationSystem.java`
- 方法：`printByOrder()`
- `RegistrationAlgorithms.java`
- 方法：`mergeSortByOrder(ArrayList<Registration> data)`
- 方法：`mergeByOrder(...)`

使用演算法：
- 歸併排序

選擇原因：
- 報名順序排序需要穩定排序，順序相同或相近時不應破壞原本資料關係。
- 歸併排序適合大量資料，時間複雜度穩定為 O(n log n)。

未採用原因：
- 不使用選擇排序，因為資料量變大時比較次數為 O(n^2)。
- 不使用插入排序作為主要方案，因為大量亂序資料時效率較差。
- 不使用 `Arrays.sort()` 或 `Collections.sort()`，因為本日練習要求自己實作排序。

## 5. 依編號查詢

實際方案：
- `EventRegistrationSystem.java`
- 方法：`searchId(String id)`
- `RegistrationAlgorithms.java`
- 方法：`mergeSortById(ArrayList<Registration> data)`
- 方法：`binarySearchById(ArrayList<Registration> sortedData, String id)`

使用演算法：
- 先用歸併排序依編號排序
- 再用二分查找

選擇原因：
- 編號查詢通常是精確查找，排序後可使用二分查找。
- 二分查找每一輪都縮小一半範圍，資料多時比順序查找更有效率。

未採用原因：
- 不直接在未排序資料上使用二分查找，因為二分查找必須建立在已排序資料上。
- 不只使用順序查找，因為資料量增加時最壞情況要逐筆比到最後。

## 6. 依姓名查詢

實際方案：
- `EventRegistrationSystem.java`
- 方法：`searchName(String name)`
- `RegistrationAlgorithms.java`
- 方法：`searchByName(ArrayList<Registration> data, String name)`

使用演算法：
- 順序查找

選擇原因：
- 姓名可能重複，查詢時需要找出所有相同姓名的資料。
- 順序查找可以完整掃描 `ArrayList`，把所有符合資料加入結果。

未採用原因：
- 不使用二分查找，因為目前主要排序鍵不是姓名，而且二分查找通常只方便找單一位置。
- 若要用二分查找找全部同名資料，還需要先依姓名排序並額外找左右邊界，對本需求較複雜。

## 7. 避免重複編號

實際方案：
- `EventRegistrationSystem.java`
- 方法：`addRegistration(String id, String name)`
- `RegistrationAlgorithms.java`
- 方法：`containsId(ArrayList<Registration> data, String id)`

使用演算法：
- 順序查找

選擇原因：
- 新增報名時，資料尚未保證依編號排序。
- 使用順序查找能直接檢查所有既有資料是否有相同編號。

未採用原因：
- 不使用二分查找，因為新增時的 `allRegistrations` 是依報名發生順序保存，不是依編號排序。
- 不使用歸併排序後再查找，因為每次新增都排序成本較高。

## 8. 維修工作優先等級排序

實際方案：
- `RepairSchedulingSystem.java`
- 方法：`printSortedByPriority()`
- `RepairAlgorithms.java`
- 方法：`mergeSortByPriorityDescending(ArrayList<RepairTask> data)`

使用資料結構與演算法：
- `ArrayList<RepairTask>`
- 歸併排序

選擇原因：
- 維修工作需要保存全部資料，再依優先等級降冪排序。
- 使用歸併排序可以在相同優先等級時保留原本順序，符合穩定排序需求。

未採用原因：
- 不使用 Queue 做優先等級排序，因為 Queue 只保證進出順序，不會自動依優先等級排列。
- 不使用 Stack，因為 Stack 只適合最近完成或最近取消類型的紀錄。

## 綜合比較

| 項目 | 適合用途 | 本日實際使用位置 | 不適合情況 |
| --- | --- | --- | --- |
| ArrayList | 保存全部資料、排序、完整掃描 | `EventRegistrationSystem.allRegistrations`、`RepairSchedulingSystem.allTasks` | 不適合直接表示先進先出或後進先出流程 |
| Queue | 候補、等待處理、先進先出 | `EventRegistrationSystem.waitingQueue`、`RepairSchedulingSystem.waitingQueue` | 不適合查詢全部資料或最近取消資料 |
| Stack | 最近取消、最近完成、後進先出 | `EventRegistrationSystem.cancelledStack`、`RepairSchedulingSystem.completedStack` | 不適合候補順序 |
| 順序查找 | 未排序資料、找全部符合項目 | `searchByName()`、`containsId()` | 大量資料的單筆精確查找效率較低 |
| 二分查找 | 已排序資料的快速精確查找 | `binarySearchById()` | 未排序資料不能直接使用 |
| 歸併排序 | 大量資料、穩定排序 | `mergeSortByOrder()`、`mergeSortByPriorityDescending()` | 需要額外暫存空間 |

## 結論

本日系統依照功能選擇資料結構：完整資料用 `ArrayList`，等待或候補用 `Queue`，最近取消或完成記錄用 `Stack`。查詢方面，未排序或要找全部資料時使用順序查找；資料已排序且查單一編號時使用二分查找。排序方面，歸併排序能保持穩定順序，並在資料量變大時比平方等級排序更適合。
