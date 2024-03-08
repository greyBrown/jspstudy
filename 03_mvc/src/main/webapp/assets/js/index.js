/**
 * 
 */

const getContextPath = ()=>{
  const host = location.host;/*localhost:8080*/
  const url = location.href/*localhost:8080/mvc/getDate.do*/
  const begin = url.indexOf(host) + host.length;
  const end = url.indexOf('/', begin + 1);
  return url.substring(begin, end);
}


 const getDateTime = ()=>{
   const type = document.getElementById('type');
   if(type.value === 'date'){
     location.href = getContextPath() + '/getDate.do';
   } else if(type.value === 'time'){
     location.href = getContextPath() + '/getTime.do';
   } else if(type.value === 'datetime'){
     location.href = getContextPath() + '/getDateTime.do';
   }
 }
 
 
 
 
 document.getElementById('btn').addEventListener('click', getDateTime);