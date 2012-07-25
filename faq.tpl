<html>
<head><title>{$title}</title></head>
<body>
	<h1>{$title}</h1>
	<div class="faq_top">
		{foreach id=section in=$sections loop=sectionIndex}
			<div cass="faq_section">
				<h4><a href="#S_{$sectionIndex.iteration}" class="faq_section_title">{$section.title}</a></h4>
				<ul>
					{foreach id=faq in=$section.faqs loop=faqIndex}
						<li><a href="#Q_{$sectionIndex.iteration}_{$faqIndex.iteration}" class="faq_question">{$faq.question|inTrim}</a></li>
					{/}
				</ul>
			</div>
		{/}
	</div>
	<hr/>
	<div class="faq_main">
		{foreach id=section in=$sections loop=sectionIndex}
			<a name="S_{$sectionIndex.iteration}" />
			<div class="faq_section">
				<h3>{$section.title}</h3>
				<ul>
					{foreach id=faq in=$section.faqs loop=faqIndex}
						<a name="Q_{$sectionIndex.iteration}_{$faqIndex.iteration}" />
						<li>
							<h4>{$faq.question|inTrim}</h4>
							<div>
								{trim}{$faq.answer}{/}
							</div>
						</li>
					{/}
				</ul>
			</div>
		{/}
	</div>
	{set i=0}{while $i < 50}<br/>{set i=$i+1}{/while}
</body>
</html>