import { useState } from "react";
import axios from "axios";
import "../styles/career.css";

function CareerPredictions(){

const [skills,setSkills] = useState("");
const [predictions,setPredictions] = useState([]);
const [loading,setLoading] = useState(false);
const [message,setMessage] = useState("");

const predictCareer = async () => {

if(!skills){
setMessage("Please enter your skills");
return;
}

try{

setLoading(true);
setMessage("");
setPredictions([]);

const response = await axios.post(
"http://localhost:8080/api/career-predict",
{
skills: skills
}
);

setPredictions(response.data);

if(response.data.length === 0){
setMessage("No career predictions found.");
}

}catch(error){

console.error(error);

if(error.response){
setMessage(error.response.data.message || "Backend error occurred");
}else{
setMessage("Server not reachable");
}

}finally{

setLoading(false);

}

};

return(

<div className="dashboard-content">

<h2 className="dashboard-title">Career Predictions</h2>

<div className="career-panel">

<div className="career-input">

<label>Your Skills</label>

<textarea
placeholder="Example: Java, Spring Boot, React, SQL, REST API"
value={skills}
onChange={(e)=>setSkills(e.target.value)}
/>

</div>

<button
className="career-btn"
onClick={predictCareer}
disabled={loading}
>
{loading ? "Predicting..." : "Predict Career"}
</button>

</div>

{message && <p className="career-message">{message}</p>}

{predictions.length > 0 && (

<div className="career-results">

<h3>Recommended Career Paths</h3>

{predictions.map((career,index)=>(

<div key={index} className="career-item">

<div className="career-name">

<strong>{career.role}</strong>

<span>{career.score}%</span>

</div>

<div className="career-bar">

<div
className="career-progress"
style={{width:`${career.score}%`}}
></div>

</div>

</div>

))}

</div>

)}

</div>

);

}

export default CareerPredictions;
