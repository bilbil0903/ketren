<?php
$KinoNami=$_POST["KinoNami"];
$turi=$_POST["turi"];
$id=$_POST["id"];
$KinoAdirisi=$_POST["KinoAdirisi"];
$KinoRasimi=$_POST["KinoRasimi"];
$KinoTuri=$_POST["KinoTuri"];
$KinoTili=$_POST["KinoTili"];
$KinoKoyulixi=$_POST["KinoKoyulixi"];
$KinoQuxandurilixi=$_POST["KinoQuxandurilixi"];
$type=$_GET["type"];
$tur1=$_POST["tur1"];
$time=$_POST["time"];
$user=$_POST["user"];
$tur2=$_POST["tur2"];
$vip=$_POST["vip"];
$android=$_POST["android"];
$ilan=$_POST["ilan"];
$POST=$_POST["POST"];
$phone=$_POST["phone"];
$isim=$_POST["isim"];
$parol1=$_POST["parol1"];
$parol2=$_POST["parol2"];
$icon=$_POST["icon"];
$nahxa=$_POST["nahxa"];
$nahxiqi=$_POST["nahxiqi"];
$adiris=$_POST["adiris"];
$nadir=$_POST["nadir"];
$admin=$_POST["admin_parol"];
require'./db.php';
mysql_query('SET NAMES utf8');
if($POST=="ENTER"){
		if($type=="yezix"&&$admin=="ketren123123"){
		if(mysql_query("insert into kino(KinoNami,KinoAdirisi,KinoRasimi,KinoTuri,KinoTili,KinoQuxandurilixi,KinoKoyulixi,time,tur1,tur2,vip) values ('$KinoNami','$KinoAdirisi','$KinoRasimi','$KinoTuri','$KinoTili','$KinoQuxandurilixi','$KinoKoyulixi','$time','$tur1','$tur2','$vip')")){
echo  "ok";
}else{
echo "E:".mysql_error();
}
}elseif($type=="nahxa_yollax"&&$admin=="ketren123123"){
		if(mysql_query("insert into mp3(nahxa,nahxiqi,adiris,see) values ('$nahxa','$nahxiqi','$adiris',0)")){
echo  "ok";
}else{
echo "E:".mysql_error();
}
	}elseif($type=="tawsiya"){
	    
		 $res=mysql_query("select KinoNami,id,KinoRasimi,KinoTuri,KinoTili,KinoQuxandurilixi,KinoKoyulixi,vip from kino  order by rand()  desc limit 6");
	if($res&&mysql_num_rows($res)){
		while($sql=mysql_fetch_assoc($res)){
			$arr[]=$sql;
		}
		echo  base64_encode(json_encode($arr,JSON_UNESCAPED_UNICODE));
	}
	}elseif($type=="tongzhi"){
$r= mysql_fetch_array(mysql_query("select id from kino order by id+0  desc limit 1"))["id"];
$r1= mysql_fetch_array(mysql_query("select KinoNami from kino order by id+0  desc limit 1"))["KinoNami"];
echo $r.",".$r1;
}elseif($type=="tuidao"){
 $res=mysql_query("select KinoNami,id,KinoRasimi,KinoTuri,KinoTili,KinoQuxandurilixi,KinoKoyulixi,vip from kino  where nadir=1  order by KinoKoyulixi+0    desc limit 500");
	if($res&&mysql_num_rows($res)){
		while($sql=mysql_fetch_assoc($res)){
			$arr[]=$sql;
		}
		echo  base64_encode(json_encode($arr,JSON_UNESCAPED_UNICODE));
	}
	}elseif($type=="baxbat"){
	    if(!is_null($turi)&&$turi!=""){
	    $turi=base64_decode($turi);
	    }
		 $res=mysql_query("select KinoNami,id,KinoRasimi,KinoTuri,KinoTili,KinoQuxandurilixi,KinoKoyulixi,vip from kino  ".$turi." order by id+0    desc limit 500");
	if($res&&mysql_num_rows($res)){
		while($sql=mysql_fetch_assoc($res)){
			$arr[]=$sql;
		}
		echo  base64_encode(json_encode($arr,JSON_UNESCAPED_UNICODE));
	}
	}elseif($type=="awat"){
	    if(!is_null($turi)&&$turi!=""){
	    $turi=base64_decode($turi);
	    }
		 $res=mysql_query("select KinoNami,id,KinoRasimi,KinoTuri,KinoTili,KinoQuxandurilixi,KinoKoyulixi,vip from kino  ".$turi."     desc limit 500");
	if($res&&mysql_num_rows($res)){
		while($sql=mysql_fetch_assoc($res)){
			$arr[]=$sql;
		}
		echo  base64_encode(json_encode($arr,JSON_UNESCAPED_UNICODE));
	}
	}elseif($type=="inkas"){
$userid = mysql_fetch_array(mysql_query("SELECT id from vip where user='$user'"))["id"];
echo $userid;
if($userid!=0&&!is_null($userid)){
$inkas=$_POST["inkas"];
$s = mysql_fetch_array(mysql_query("SELECT inkas from kino where id='$id'"))["inkas"];
if($userid!=""&&$inkas!=""&&strlen($s)>0&&!is_null($s)){
$p1=json_encode(["userID"=>$userid,"time"=>date('Y-m-d H:i:s'),"android"=>$android,"inkas"=>$inkas]);
if(is_null($s)){$s="[]";}
$s=json_decode($s,TRUE);
for($i=0;$i<count($s);$i++){
	$p=$s[$i];
	$p=json_encode($p);
	if($p!=$p1&&$i==count($s)-1){
	$s[]=["userID"=>$userid,"time"=>date('Y-m-d H:i:s'),"android"=>$android,"inkas"=>$inkas];
	}
}
$s=json_encode($s);
mysql_query("update kino set inkas='$s' where  id='$id' ");
}elseif(strlen($s)==0||is_null($s)){
$s=json_encode(array(["userID"=>$userid,"time"=>date('Y-m-d H:i:s'),"android"=>$android,"inkas"=>$inkas]));
mysql_query("update kino set inkas='$s' where  id='$id' ");
}
}
}elseif($type=="get_inkas"){
$s = mysql_fetch_array(mysql_query("SELECT inkas from kino where id='$id'"))["inkas"];
echo $s;
}elseif($type=="inkas_uqur"){
$s = mysql_fetch_array(mysql_query("SELECT name from vip where id='$id'"))["name"];
$s1 = mysql_fetch_array(mysql_query("SELECT icon from vip where id='$id'"))["icon"];
echo $s.",".$s1;
	}elseif($type=="kiska"){
	    if(!is_null($turi)&&$turi!=""){
	    $turi=base64_decode($turi);
	    }
		 $res=mysql_query("select KinoNami,KinoRasimi,KinoAdirisi from kino where KinoTuri='kiska' and tur1=".$turi." order by  rand()   desc limit 50");
	if($res&&mysql_num_rows($res)){
		while($sql=mysql_fetch_assoc($res)){
			$arr[]=$sql;
		}
		echo  json_encode($arr,JSON_UNESCAPED_UNICODE);
	}
	}elseif($type=="nahxa"){
	 $res=mysql_query("select id,nahxa,nahxiqi,see,adiris from mp3 order by id+0 desc limit 500");
	if($res&&mysql_num_rows($res)){
		while($sql=mysql_fetch_assoc($res)){
			$arr[]=$sql;
		}
		echo  json_encode($arr,JSON_UNESCAPED_UNICODE);
	}
	}elseif($type=="nahxa2"){
	 $res=mysql_query("select id,nahxa,nahxiqi,see,adiris from mp3 order by see+0 desc limit 500");
	if($res&&mysql_num_rows($res)){
		while($sql=mysql_fetch_assoc($res)){
			$arr[]=$sql;
		}
		echo  json_encode($arr,JSON_UNESCAPED_UNICODE);
	}	
	}elseif($type=="nahxa_geci"){
     $g=mysql_fetch_array(mysql_query( "select geci from mp3 where id='$id'"))["geci"];
		echo $g;
		}elseif($type=="see"&&!is_null($id)){
$sql = "SELECT KinoKoyulixi from kino where id='$id'";
		$query = mysql_query($sql);
$row = mysql_fetch_array($query);
        $ss=$row["KinoKoyulixi"];
		mysql_query("update kino set KinoKoyulixi=$ss+1 where id='$id'");
$row1 = mysql_fetch_array(mysql_query("SELECT see from vip where user='$user'"));
        $s=$row1["see"];
        if(!is_null($user)){
		mysql_query("update vip set see=$s+1 where user='$user'");
        }
        }elseif($type=="nahxa_see"&&!is_null($id)){
$sql = "SELECT see from mp3 where id='$id'";
		$query = mysql_query($sql);
$row = mysql_fetch_array($query);
        $ss=$row["see"];
		mysql_query("update mp3 set see=$ss+1 where id='$id'");
		echo "ok";
		}elseif($type=="izdax"){
$res=mysql_query("select KinoNami,id,KinoRasimi,KinoTuri,KinoTili,KinoQuxandurilixi,KinoKoyulixi,vip from  kino where  KinoNami LIKE '%".$KinoNami."%' order by id+0    desc limit 100 "); 
	if($res&&mysql_num_rows($res)){
		while($sql=mysql_fetch_assoc($res)){
			$arr[]=$sql;
		}
		echo base64_encode(json_encode($arr,JSON_UNESCAPED_UNICODE));
	}
		}elseif($type=="imza_koyux"){
$sql = "SELECT wakit from vip where user='$user'";
		$query = mysql_query($sql);
$row = mysql_fetch_array($query);
        $wakit=$row["wakit"];
		$today = strtotime(date('Y-m-d'));
		
		if($wakit<$today&&!is_null($user)){
mysql_query("update vip set wakit=$today where  user='$user' ");
$sql = "SELECT tagga from vip where user='$user'";
		$query = mysql_query($sql);
$row = mysql_fetch_array($query);
        $tagga=$row["tagga"];
mysql_query("update vip set tagga=$tagga+1 where  user='$user' ");
$sql = "SELECT tagga from vip where user='$user'";
		    echo "ok";
		}else{
		    echo "no";
		}
		}elseif($type=="imza"){
$sql = "SELECT wakit from vip where user='$user'";
		$query = mysql_query($sql);
$row = mysql_fetch_array($query);
        $wakit=$row["wakit"];
		$today = strtotime(date('Y-m-d'));
		if($wakit<$today){
		    echo "ok";
		}else{
		    echo "no";
		}
		}elseif($type=="tagga"){
$sql = "SELECT tagga from vip where user='$user'";
		$query = mysql_query($sql);
$row = mysql_fetch_array($query);
        $tagga=$row["tagga"];
        echo $tagga;
		}elseif($type=="izdax_key"){
$res=mysql_query("select KinoNami from  kino where  KinoNami LIKE '%".$KinoNami."%' order by id+0    desc limit 15 "); 
	if($res&&mysql_num_rows($res)){
		while($sql=mysql_fetch_assoc($res)){
			$arr[]=$sql;
		}
		echo base64_encode(json_encode($arr,JSON_UNESCAPED_UNICODE));
	}		
		}elseif($type=="admin_enter"&&$admin=="ketren123123"){
	  echo "ok";
		}elseif($type=="izdax_admin"&&$admin=="ketren123123"){
$res=mysql_query("select KinoNami,KinoRasimi,KinoAdirisi,KinoTuri,KinoTili,vip,KinoQuxandurilixi,id,nadir from  kino where  KinoNami LIKE '%".$KinoNami."%' order by id+0    desc limit 100 "); 
	if($res&&mysql_num_rows($res)){
		while($sql=mysql_fetch_assoc($res)){
			$arr[]=$sql;
		}
		echo base64_encode(json_encode($arr,JSON_UNESCAPED_UNICODE));
	}	
		}elseif($type=="xiugai"){
		    $parol1=md5($parol1);
            $parol2=md5($parol2);
if(!is_null($isim)&&!is_null($user)){
mysql_query("update vip set name='$isim' where  user='$user' ");
}
if(!is_null($parol2)&&!is_null($user)){
mysql_query("update vip set parol='$parol2' where  user='$user'");
}
if(!is_null($icon)&&!is_null($user)){
mysql_query("update vip set icon='$icon' where  user='$user'");
}
}elseif($type=="kirix"){
    $parol1=md5($parol1);
$query = mysql_query("select user from vip where name='$isim' and parol='$parol1'");
$row = mysql_fetch_array($query);
        $user=$row["user"];
        echo $user;
}elseif($type=="get_user"&&!is_null($user)){
$query = mysql_query("select name,icon from vip where user='$user'");
$row = mysql_fetch_array($query);
        $user=$row["name"];
        $icon=$row["icon"];
        echo '["'.$user.'","'.$icon.'"]';
        
	}elseif($type=="save_kino"&&$admin="ketren123123"){
	    $m=$KinoNami;
mysql_query("update kino set KinoNami='$KinoNami' where  id='$id'");
mysql_query("update kino set KinoAdirisi='$KinoAdirisi' where id='$id'");
mysql_query("update kino set KinoRasimi='$KinoRasimi' where  id='$id'");
mysql_query("update kino set KinoTili='$KinoTili' where  id='$id'");
mysql_query("update kino set KinoQuxandurilixi='$KinoQuxandurilixi' where  id='$id'");
mysql_query("update kino set KinoTuri='$KinoTuri' where  id='$id'");
mysql_query("update kino set vip='$vip' where  id='$id'");
mysql_query("update kino set nadir='$nadir' where  id='$id'");
echo "ok";
}elseif($type=="save_ilan"&&$admin="ketren123123"){
$myfile = fopen("ilan.php", "w") or die("Unable to open file!");
fwrite($myfile, $ilan);
fclose($myfile);
}elseif($type=="buy"&&$admin="ketren123123"){
mysql_query("update vip set time='$time' where  user='$user'");
mysql_query("update vip set v=1 where  user='$user'");
echo "ok";
}elseif($type=="delete"&&$admin=="ketren123123"){
mysql_query("delete from kino  where id=$id");
echo "ok";
		}elseif($type=="kisim"){
$sql = "SELECT KinoAdirisi from kino where id='$id'";
		$query = mysql_query($sql);
$row = mysql_fetch_array($query);
        $adiris=$row["KinoAdirisi"];
$adiris=count(split(',',$adiris));
		echo $adiris;
		}elseif($type=="get_kisim"){
$id=split(',',$id);
		    $ki=$id[1];
		    $id=$id[0];
$kur = mysql_fetch_array(mysql_query("SELECT tagga from vip where user='$android'"));
        $tg=$kur["tagga"];
$zero1=strtotime (date("y-m-d")); 
$t= strtotime(mysql_fetch_array(mysql_query("SELECT time from vip where user='$android'"))["time"]);
$v= mysql_fetch_array(mysql_query("SELECT vip from kino where id='$id'"))["vip"];
$uv= mysql_fetch_array(mysql_query("SELECT v from vip where user='$android'"))["v"];
        if($v==1&&$uv==1&&$t>$zero1){
$sql = "SELECT KinoAdirisi from kino where id='$id'";
		$query = mysql_query($sql);
$row = mysql_fetch_array($query);
        $adiris=$row["KinoAdirisi"];
        $adiris=split(',',$adiris)[$ki];
	echo base64_encode(getrealurl($adiris));
        }elseif($v==0){
$sql = "SELECT KinoAdirisi from kino where id='$id' and vip=0";
		$query = mysql_query($sql);
$row = mysql_fetch_array($query);
        $adiris=$row["KinoAdirisi"];
$adiris=split(',',$adiris)[$ki];
	echo base64_encode(getrealurl($adiris));
        }elseif($tg>0){
mysql_query("update vip set tagga=$tg-1 where  user='$android' ");
$sql = "SELECT KinoAdirisi from kino where id='$id'";
		$query = mysql_query($sql);
$row = mysql_fetch_array($query);
        $adiris=$row["KinoAdirisi"];
        $adiris=split(',',$adiris)[$ki];
	echo base64_encode(getrealurl($adiris));
        }else{
            echo "NONE_VIP";
        }
		}elseif($type=="AND"){
$r= mysql_fetch_array(mysql_query("SELECT user from vip where user='$android'"))["user"];
if(is_null($r)){
mysql_query("insert into vip(user,v,time,phone) values ('$android',0,'0','$phone')");
}
}else if($type=="root"){
$uv= mysql_fetch_array(mysql_query("SELECT v from vip where user='$android'"))["v"];
$zero1=strtotime(date("y-m-d")); 
$t=strtotime(mysql_fetch_array(mysql_query("SELECT time from vip where user='$android'"))["time"]);
$vip_root=ceil(($t-$zero1)/86400);
if($vip_root>0){
    echo $vip_root;
}else if($vip_root<0&&$uv==1){
    echo "USED_VIP";
}else{
    echo "NONE_VIP";
}
		}else{
	echo "Yoldin Adixip Kapsiz!";
		}
}else{
    echo "Do You Hacker?....(.__.)";
}
function getrealurl($url){
$n=strpos($url,"vid=");
$str=substr($url,$n+4,11);
$ur="http://vv.video.qq.com/getinfo?vid=".$str."&platform=101001&charge=0&otype=json&defn=shd";
$str=file_get_contents($ur);
$json_data=str_replace(";","",$str);
$json_data=str_replace("QZOutputJson=","",$json_data);
  $json_data=json_decode($json_data,TRUE);
$fvkey= $json_data['vl']['vi'][0]['fvkey'];
$filename =$json_data['vl']['vi'][0]['fn'];
    $baseUrl = $json_data['vl']['vi'][0]['ul']['ui'][3]['url'];
    $result =$baseUrl.$filename.'?vkey=' . $fvkey;
$header = get_headers($url,1);
if(strpos($url,".m3u8")>0||strpos($url,"mp4")>0||strpos($url,"wangyi")>0){
return $url;
    }elseif (strpos($header[0],'301') || strpos($header[0],'302')) {
        if(is_array($header['Location'])) {
            return $header['Location'][count($header['Location'])-1];
        }else{
            return $header['Location'];
        }
    }else{
    return $result;
    }
}

?>