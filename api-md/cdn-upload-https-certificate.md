# 证书管理-上传HTTPS证书

## 请求
<table width="100%" border="1" cellspacing="0" cellpadding="0">
  <th align="left"><strong>方法</strong>
    </td>
  <th align="left"><strong>请求 URI</strong>
    </td>  
  <tr>
    <td>POST</td>
    <td>https://api-preview.cdn.azure.cn/subscriptions/{subscriptionId}/https/certificates?apiVersion=1.0</td>
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
    <tr>
    <td>Content-Type</td>
    <td>必填为application/json。</td>
  </tr>
  
</table>

### 请求 Body
```
{
    "Name": "neoapitestcert2",
    "PublicCertificate": "MIIDNDCCAhygAwIBAgIQBdSVCj/BTAemTRD2b6sSCjANBgkqhkiG9w0BAQsFADAXMRUwEwYDVQQDEwxLRVlWQVVMVERFTU8wHhcNMTcwNTAzMDczNDExWhcNMTgwNTAzMDc0NDExWjAXMRUwEwYDVQQDEwxLRVlWQVVMVERFTU8wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDkZlh01m5vpwFvFaffSHzcJRl8mZtLpo4K4p8Hed+QH/LyVcynibPKezupYJ1wgq0pzr7+d4UK35LTh9hAdAs/esQ8hc++fwoKhSqm9z8eTWuNL3UanmHR6Fb9nDFOw8TOBwblJtuStMeXZgkV+AfNB6MFCt7uwyeY+ZRuPv+3coh44QI+X3EVFNzEl/fCgYfool9BqZrlZsz8wLxXnbZ+mcBnw5cOAGOcPoZdZySXfxQCdmF7E5E3h/d/QfCHr/4cNU/qZeuIgjEbeV969dHZR443/dNxBQ/f7LlN1ncmEGNCnZONgAHdgBigDm8/3ZWijR+JEcBoJjcvl8+J/AmJAgMBAAGjfDB6MA4GA1UdDwEB/wQEAwIFoDAJBgNVHRMEAjAAMB0GA1UdJQQWMBQGCCsGAQUFBwMBBggrBgEFBQcDAjAfBgNVHSMEGDAWgBS0cptvhWVz2d2AmhXvGV0MaYRhMDAdBgNVHQ4EFgQUtHKbb4Vlc9ndgJoV7xldDGmEYTAwDQYJKoZIhvcNAQELBQADggEBAOMXdHZMAxGBNvIe4Cz/6TWTVC462twzNJgbmoKxOjGzMUcau+33VmnZ31v2hKdDzkmxVP/uvzn+puv3U5TogJKcK8u1boT7cUpT6j/jT9xHxwzfcqBt5E+YYbn/bdnTHRjMjBaleZPhNHBL8oR+tftqlR7WtMJRenRJCQdoC7OqLZSELg5ZpWjEkIp1MpUw68HM1LEe3SigLUo/Fp1nZbjIWo1RmwORkQd25OY4NUuiHU2G1ZYqD3VXHReoi2jtzXk20/SCqdCe/ffhZQqZYEego7M95aFMcCh/kHbsq9UUs/TnN09N6jDqgmOtpUUhWwrn57quTVwcUEAjwrPDx3s=",
    "PrivateKey": "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDkZlh01m5vpwFvFaffSHzcJRl8mZtLpo4K4p8Hed+QH/LyVcynibPKezupYJ1wgq0pzr7+d4UK35LTh9hAdAs/esQ8hc++fwoKhSqm9z8eTWuNL3UanmHR6Fb9nDFOw8TOBwblJtuStMeXZgkV+AfNB6MFCt7uwyeY+ZRuPv+3coh44QI+X3EVFNzEl/fCgYfool9BqZrlZsz8wLxXnbZ+mcBnw5cOAGOcPoZdZySXfxQCdmF7E5E3h/d/QfCHr/4cNU/qZeuIgjEbeV969dHZR443/dNxBQ/f7LlN1ncmEGNCnZONgAHdgBigDm8/3ZWijR+JEcBoJjcvl8+J/AmJAgMBAAECggEAGjft7k6Zu48rJNhybnkcnSxUlEcxSqI+Ur1QRlxNB7dFLSCghzhxVzxlfURqGkrj7CVPqzgm4XhH+2iQEFkJ3AIAohZ1wi9E5K+D9YnzGMxIHPtOSO2WutD+IGuERP+H2WM43UgEQ19lfImR4C/aXGTEp8znSbcFFgRLb8rjUahYfqmGzLSOYFCAAl0p/WutEfGXF0v83b/6M6SlbaPrjgDBDSpZeU5nUzJ/vA+QXFDimPYkflV8eXhlqLckwuXAonefvplxnzr5dI0EqB8TYcGaRNVjbDQLa0r3RhxSKLKAYNFjxhsa7YMq/9OW+bp9Xg5M4B+eP1tv+2qh9mtLIQKBgQDsURTa9K+JAzW0E+c2h8EIjcGdc1R7XQiYWiYd/cmyJa4lYNhdY9X3EPBG4FJk1jnOMjzMX4GN7Exz0EyAkEQS3o+z2y/MFM5sMKOD8RfVdKmBCAlfqxCtdOlkAR7g/ohuMCFKzqQX8/mfHROXyyhHUewaFqLE6KIKPR8/PLFN6QKBgQD3bHQNC0aCXhdP09cUfFLDwaweQJSJv6B/tM01NvED65xOBOs2Rgl641FQGMA5otUTUgfpN92dDpb3MXxV1eUENLfp9+dv+FMeMbrwM6k3W3OWY0bzHOtrBR1WTzXzTE5bftB3owg8DYP9fJ0Ou5Pszyqb5ueMqgY2TSpf7/F6oQKBgQCsvQPynQMmstrxm87Z1Fesyfnp/qCVYEblHphYOefSOarXFbhyY4TGiXIpTNXNca5lY+F/PpyzwjOMQIl7PHkWFeuS+7gvhFHzUYZk7rmORS2V7Or1LFUPhbMIb61Dp6rQaIwdY3kdLTCdNyLYRR9XKrHbzAoEx0uTEmSh09qC6QKBgQC4EOvI5HGA/OSU04D3UYH09bjJDLw0OeX5+5SKGSR2CSrP8+LUDohw7nmIf9FJT3T6mZUfEBuDAt6WaS8ZYDWuylVj7rQzXWbgWJi8p4Ikl5lG8PZQWFxe0oA/vE/jfwnwEUifKB8xJdDinHjvA+cJThlhwAbUY4yZixCSLJvV4QKBgCbmeVmquPre6jUsg9/Y7BJVi0R/CYK0KJK1RDkSshOM16oqTzTL5tJ1hNdoUNbOINsmB2seS+ARqLXMTjgjS4jiIMdvxRi3tLvE2P7VfxbcVFLK5yEewm9xcNmwGb2earxmUDYxCJ8ZSyGRkaDAK3qjmjwR0uDQqrYpxXvgLAEq"
}

```

<table width="100%" border="1" cellspacing="0" cellpadding="0">
  <th align="left"><strong>参数名称</strong>
  </th>
  <th align="left"><strong>描述</strong>
  </th>
  <tr>
    <td>Name</td>
    <td>HTTPS证书名字</td>
  </tr>
  <tr>
    <td>PublicCertificate</td>
    <td>Base64编码公证书, pem格式中介于-----BEGIN CERTIFICATE-----和-----END CERTIFICATE-----中间的部分,中间不包含回车换行。</td>
  </tr>
    <tr>
    <td>PrivateKey</td>
    <td>Base64编码私钥，pem格式中介于-----BEGIN PRIVATE KEY-----和-----END PRIVATE KEY-----中间的部分，中间不包含回车换行。</td>
  </tr>
</table>

## 响应

响应由状态码，响应 headers以及响应 body组成。
### 状态码
<table width="100%" border="1" cellspacing="0" cellpadding="0">
  <th align="left"><strong>状态码</strong>
    </td>
  <th align="left"><strong>描述</strong>
    </td>
  <tr>
    <td>200</td>
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
  "SubscriptionId": "b520c544-ec34-4ac4-86f5-5394363919c3",
  "CertificateName": "mycertificate",
  "CertificateId": "db9ada2b-d84e-4fa5-846f-914c93698f15"
}
```
<table width="100%" border="1" cellspacing="0" cellpadding="0">
  <th align="left"><strong>参数名称</strong>
    </td>
  <th align="left"><strong>描述</strong>
    </td>
  <tr>
    <td>SubscriptionId</td>
    <td>订阅唯一标识</td>
  </tr>
  <tr>
    <td>CertificateName</td>
    <td>HTTPS证书名字</td>
  </tr>
    <tr>
    <td>CertificateId</td>
    <td>HTTPS证书唯一标识</td>
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
