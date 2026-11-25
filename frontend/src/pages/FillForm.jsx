import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import api from '../api/axios';
export default function FillForm(){
 const {id}=useParams();const nav=useNavigate();const [form,setForm]=useState(null);const [error,setError]=useState('');
 const {register,handleSubmit,formState:{errors}}=useForm();
 useEffect(()=>{api.get(`/forms/${id}`).then(r=>setForm(r.data)).catch(()=>setError('Unable to load this form'))},[id]);
 const submit=async values=>{try{await api.post(`/forms/${id}/submissions`,{answers:values});nav('/forms');}catch(e){setError(e.response?.data?.message||'Submission failed')}};
 if(!form)return <div className="builder-panel">{error||'Loading form...'}</div>;
 const control=(f)=>{const common={...register(String(f.id),{required:f.required?`${f.label} is required`:false,...(f.type==='EMAIL'?{pattern:{value:/^[^\s@]+@[^\s@]+\.[^\s@]+$/,message:'Enter a valid email'}}:{})})};if(f.type==='TEXTAREA')return <textarea className="form-control" {...common}/>;if(f.type==='DROPDOWN')return <select className="form-select" {...common}><option value="">Choose...</option>{(f.options||[]).map(o=><option key={o}>{o}</option>)}</select>;if(f.type==='CHECKBOX')return <input type="checkbox" className="form-check-input" {...common}/>;return <input className="form-control" type={f.type.toLowerCase()} {...common}/>};
 return <><div className="page-heading"><div><p className="eyebrow">SUBMISSION</p><h1>{form.title}</h1><p className="muted">{form.description}</p></div></div><form className="builder-panel" onSubmit={handleSubmit(submit)}>{error&&<div className="alert alert-danger">{error}</div>}{form.fields.map(f=><div className="mb-3" key={f.id}><label className="form-label fw-semibold">{f.label}{f.required?' *':''}</label><div>{control(f)}</div>{errors[String(f.id)]&&<small className="text-danger">{errors[String(f.id)].message}</small>}</div>)}<button className="btn btn-primary">Submit response</button></form></>;
}
