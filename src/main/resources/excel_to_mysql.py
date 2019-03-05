import sys
import pandas as pd  
from sqlalchemy import create_engine
file_name = sys.argv[1]
# path =  'D:/Software/Program Files/apache-tomcat-9.0.16/webapps/ModelManage/files/ModelManage/'
path = 'D:/Project/dclab/datasyetem/target/classes/static/files/upload/'
df = pd.read_excel(path + file_name)
loc = file_name.find('.')
file_name = file_name.replace(' ', '')
# connect = create_engine('mysql+pymysql://root:123456@202.120.40.111:3306/sdmWindows?charset=utf8')
connect = create_engine('mysql+pymysql://root:admin@localhost:3306/powersystem?charset=utf8')
pd.io.sql.to_sql(df, file_name, connect, schema='powersystem', if_exists='append')