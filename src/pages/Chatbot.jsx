import { useState } from "react";
import axios from "axios";
import "../styles/mentor.css";

function AIMentor(){

const [question,setQuestion] = useState("");
const [messages,setMessages] = useState([]);
const [loading,setLoading] = useState(false);

const askMentor = async () => {

if(!question.trim()) return;

const userMsg = { type:"user", text:question };

setMessages(prev => [...prev,userMsg]);
setQuestion("");
setLoading(true);

try{

const res = await axios.post(
"http://localhost:8080/api/mentor",
{
question:question
}
);

const aiMsg = {
type:"ai",
text:res.data.answer
};

setMessages(prev => [...prev,aiMsg]);

}catch(error){

setMessages(prev => [
...prev,
{type:"ai",text:"Something went wrong. Please try again."}
]);

}

setLoading(false);

};

return(

<div className="mentor-page">

<h2 className="mentor-title">AI Mentor</h2>

<div className="chat-container">

<div className="chat-messages">

{messages.map((msg,index)=>(

<div
key={index}
className={msg.type === "user" ? "message user" : "message ai"}
>
{msg.text}
</div>
))}

{loading && (

<div className="message ai">
Thinking...
</div>
)}

</div>

<div className="chat-input">

<textarea
placeholder="Ask your AI mentor..."
value={question}
onChange={(e)=>setQuestion(e.target.value)}
onKeyDown={(e)=>{
if(e.key==="Enter" && !e.shiftKey){
e.preventDefault();
askMentor();
}
}}
/>

<button onClick={askMentor} disabled={loading}>
Send
</button>

</div>

</div>

</div>

);

}

export default AIMentor;
