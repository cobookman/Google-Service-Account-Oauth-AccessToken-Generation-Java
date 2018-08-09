1) Create a service account in your GCP project. Download JSON key and copy it
into `client_secret.json`.
2) Enable the Google Plus API:
https://pantheon.corp.google.com/apis/api/plus.googleapis.com/overview.
Note that it might take a few minutes for the enablement to propegate.
3) Run the Main Method in src/main/Main.java

Sample Script Output:
```
Access Token: ya29.c.Elr0BdOlSfVeAbMLHM9RCLilRiP67k3VC4O0RM1hSM2bdvW5h5JrpfuLoATWeQVTr-R-ckljgA3L0N5CLISeYAANCnAZODTGny86Z3h6OsTWBngDCyHPyez8Uow
{
 "kind": "plus#person",
 "etag": "\"jb1Xzanox6i8Zyse4DcYD8sZqy0/dRcPZ8ia1S0tpdJR00mTijXHC_8\"",
 "emails": [
  {
   "value": "example-service-account@ddos-route-proxy.iam.gserviceaccount.com",
   "type": "account"
  }
 ],
 "objectType": "person",
 "id": "112635163196402868985",
 "displayName": "",
 "name": {
  "familyName": "",
  "givenName": ""
 },
 "image": {
  "url": "https://lh3.googleusercontent.com/-fQjWS-ke9S4/AAAAAAAAAAI/AAAAAAAAAAA/vJ2e6Lo--_0/photo.jpg?sz=50",
  "isDefault": true
 },
 "isPlusUser": false,
 "language": "en_US",
 "verified": false
}
```
