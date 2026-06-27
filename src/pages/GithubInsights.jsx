import { useState } from "react";
import axios from "axios";
import "../styles/github.css";

function GitHubInsights(){

const [username,setUsername] = useState("");
const [data,setData] = useState(null);
const [loading,setLoading] = useState(false);

const fetchGitHub = async () => {

if(!username){
alert("Please enter GitHub username");
return;
}

try{

setLoading(true);

const response = await axios.get(
`http://localhost:8080/api/github/${username}`
);

setData(response.data);

}
catch(error){
console.error("Error fetching GitHub data",error);
alert("User not found or server error");
}
finally{
setLoading(false);
}

};

return(

<div className="github-container">

<div className="github-panel">

<h2>GitHub Insights</h2>

<div className="github-input-group">

<label>GitHub Username</label>

<input
type="text"
placeholder="Enter username"
value={username}
onChange={(e)=>setUsername(e.target.value)}
/>

</div>

<button className="github-btn" onClick={fetchGitHub}>
{loading ? "Analyzing..." : "Analyze GitHub"}
</button>

</div>


{data && (

<>

<div className="github-profile">

<img
src={data.avatar}
alt="avatar"
className="github-avatar"
/>

<div className="github-profile-info">

<h3>{data.username}</h3>

<p>{data.bio || "GitHub Developer"}</p>

</div>

</div>


<div className="github-stats">

<div className="github-stat-card">
<h4>Repositories</h4>
<p>{data.repositories}</p>
</div>

<div className="github-stat-card">
<h4>Followers</h4>
<p>{data.followers}</p>
</div>

<div className="github-stat-card">
<h4>Stars</h4>
<p>{data.stars}</p>
</div>

<div className="github-stat-card">
<h4>Main Language</h4>
<p>{data.mainLanguage}</p>
</div>

</div>

</>

)}

</div>

);

}

export default GitHubInsights;