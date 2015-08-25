# AndroidEncryptRSA

# JavaDencryptRSA
encrypt data public certificate

Install openssl

openssl genrsa -des3 -out private.key 4096

openssl req -new -x509 -key private.key -out public.crt
