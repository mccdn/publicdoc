# 节点管理-启用处于禁用状态节点

## 请求
<table width="100%" border="1" cellspacing="0" cellpadding="0">
  <th align="left"><strong>方法</strong>
    </td>
  <th align="left"><strong>请求 URI</strong>
    </td>  
  <tr>
    <td>POST</td>
    <td>https://api-preview.cdn.azure.cn/subscriptions/{subscriptionId}/endpoints/{endpointId}/enable?apiVersion=1.0</td>
  </tr>
</table>

### URI参数
<table width="100%" border="1" cellspacing="0" cellpadding="0">
  <th align="left"><strong>参数名</strong>
    </td>
  <th align="left"><strong>描述</strong>
    </td>  
  <tr>
    <td>subscriptionId</td>
    <td>订阅唯一标识</td>
  </tr
  <tr>
    <td>endpointId</td>
    <td>目标节点唯一标识</td>
  </tr>
</table>

### 请求 Headers
<table width="100%" border="1" cellspacing="0" cellpadding="0">
  <th align="left"><strong>请求包头</strong>
    </td>
  <th align="left"><strong>描述</strong>
    </td>

  <tr>
    <td>x-azurecdn-request-date</td>
    <td>必填。符合yyyy-MM-dd hh:mm:ss格式的UTC当前请求时间</td>
  </tr>
  <tr>
    <td>Authorization</td>
    <td>必填。授权头，具体算法见授权请求头计算。</td>
  </tr>
</table>

### 请求 Body
无

## 响应

响应由状态码，响应 headers以及响应 body组成。
### 状态码
<table width="100%" border="1" cellspacing="0" cellpadding="0">
  <th align="left"><strong>状态码</strong>
    </td>
  <th align="left"><strong>描述</strong>
    </td>
  <tr>
    <td>202</td>
    <td>表明服务器成功接受请求</td>
  </tr>
  <tr>
    <td>其他</td>
    <td>表示出错的通用回复</td>
  </tr>
</table>

### 响应 Headers

<table width="100%" border="1" cellspacing="0" cellpadding="0">
  <th align="left"><strong>响应包头</strong>
    </th>
  <th align="left"><strong>描述</strong>
    </th>

  <tr>
    <td>X-Correlation-Id</td>
    <td>该请求唯一标识，用于追踪请求信息。</td>
  </tr>
</table>

### 响应 Body
**请求成功的JSON示例**:
```
{
  "Succeeded": true,
  "IsAsync": true,
  "AsyncInfo": {
    "TaskTrackId": "b520c544-ec34-4ac4-86f5-5394363919c3",
    "TaskStatus": "Processing"
  }
}
```
<table width="100%" border="1" cellspacing="0" cellpadding="0">
  <th align="left"><strong>参数名称</strong>
    </td>
  <th align="left"><strong>描述</strong>
    </td>

  <tr>
    <td>TaskTrackId</td>
    <td>操作唯一标识，可用于查询进度</td>
  </tr>
  <tr>
    <td>TaskStatus</td>
    <td>任务状态。
        <ul>
         <li>NotSet: 状态未知</li>
         <li>Processing: 正在处理</li>
         <li>Succeeded: 成功</li>
         <li>Failed: 失败</li>
        </ul>
    </td>
  </tr>
</table>

**请求失败的JSON示例**:
```
{
  "Succeeded": false,
  "ErrorInfo": {
    "Type": "MissingAuthorizationHeader",
    "Message": "Missing authorization header."
  }
}
```
<table width="100%" border="1" cellspacing="0" cellpadding="0">
  <th align="left"><strong>参数名称</strong>
    </td>
  <th align="left"><strong>描述</strong>
    </td>

  <tr>
    <td>Type</td>
    <td>错误类型
         <ul>
            <li>CredentialInvalid:凭据不合法</li>
            <li>ParameterMissing:缺少参数</li>
            <li>ParameterInvalid：参数不合法</li>
            <li>MissingAuthorizationHeader:缺少Authorization请求头</li>
            <li>InvalidRequestDateHeader:请求时间不合法</li>
            <li>MissingRequestDateHeader：缺少请求时间头</li>
            <li>AuthorizationHeaderExpired:Authorization请求头已失效</li>
            <li>InvalidAuthorizationHeader:Authorization请求头不合法</li>
            <li>ApiKeyNotFound：API密钥不存在</li>
            <li>InvalidApiKey:API密钥不合法</li>
            <li>WrongSignature:签名不对</li>
            <li>SubscriptionNotFound：订阅不存在</li>
            <li>EndpointDoesNotBelongToSubscription：节点不属于订阅</li>
            <li>EndpointNotInActiveState：节点不处于活跃状态</li>
            <li>EndpointNotFound：节点不存在</li>
            <li>MaliciousItemPathDetected：检查到恶意路径</li>
            <li>PermissionDenied：权限不够</li>
            <li>RequestThrottled：请求被限流</li>
         </ul>    
    </td>
  </tr>
  <tr>
    <td>Message</td>
    <td>错误信息</td>
  </tr>
</table>
