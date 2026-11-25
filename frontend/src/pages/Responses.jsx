import { useEffect, useState } from 'react';
import api from '../api/axios';
export default function Responses(){
 const [items,setItems]=useState([]);const [error,setError]=useState('');
 useEffect(()=>{api.get('/submissions').then(r=>setItems(r.data)).catch(e=>setError(e.response?.data?.message||'Could not load responses'))},[]);
 return <><div className="page-heading"><div><p className="eyebrow">ADMINISTRATION</p><h1>Submitted responses</h1><p className="muted">Responses received from users.</p></div></div>{error&&<div className="alert alert-danger">{error}</div>}<div className="builder-panel table-responsive"><table className="table align-middle"><thead><tr><th>Form</th><th>Submitted by</th><th>Submitted at</th><th>Answers</th></tr></thead><tbody>{items.map(s=><tr key={s.id}><td>{s.formTitle}</td><td>{s.userName}</td><td>{new Date(s.submittedAt).toLocaleString()}</td><td><details><summary>View answers</summary><pre className="answer-json">{JSON.stringify(s.answers,null,2)}</pre></details></td></tr>)}</tbody></table>{!items.length&&!error&&<div className="empty-state">No submissions yet.</div>}</div></>;
}
